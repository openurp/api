/*
 * Beangle, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2005-2016, Beangle Software.
 *
 * Beangle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Beangle is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Beangle.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.beangle.commons.model.bind

import scala.collection.JavaConverters.asScalaSet
import scala.language.implicitConversions
import scala.reflect.ClassTag
import scala.reflect.runtime.{ universe => ru }

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.commons.lang.reflect.BeanInfos
import org.beangle.commons.logging.Logging
import org.beangle.commons.model.bind.Binder.{ Collection, CollectionProperty, Column, ColumnHolder, ComponentProperty, Entity, IdGenerator, Index, ManyToOneProperty, MapProperty, Property, SeqProperty, SimpleKey, ToManyElement, TypeNameHolder }
import org.beangle.commons.model.bind.Mapping.EntityHolder
import org.beangle.commons.model.bind.Binder.ToOneProperty

object Mapping {

  val OrderColumnName = "idx"

  trait Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit
  }

  class NotNull extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      property.columns foreach (c => c.nullable = false)
    }
  }

  class Unique extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      property.columns foreach (c => c.unique = true)
    }
  }

  class ElementColumn(name: String) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      val mp = cast[MapProperty](property, holder, "element column should used on map")
      val ch = mp.element.get.asInstanceOf[ColumnHolder]
      ch.columns foreach (x => x.name = name)
    }
  }

  class ElementLength(len: Int) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      val mp = cast[MapProperty](property, holder, "element length should used on map")
      val ch = mp.element.get.asInstanceOf[ColumnHolder]
      ch.columns foreach (x => x.length = Some(len))
    }
  }

  class Cache(val cacheholder: CacheHolder) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      cacheholder.add(List(new Collection(holder.clazz, property.name)))
    }
  }

  class TypeSetter(val typeName: String) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      cast[TypeNameHolder](property, holder, "TypeNameHolder needed").typeName = Some(typeName)
    }
  }

  class One2Many(targetEntity: Option[Class[_]], mappedBy: String, private var cascade: Option[String] = None) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      val collp = cast[CollectionProperty](property, holder, "one2many should used on seq")
      collp.key = Some(new SimpleKey(new Column(columnName(mappedBy, true))))
      val ele = collp.element.get.asInstanceOf[ToManyElement]
      ele.columns.clear
      ele.one2many = true
      targetEntity foreach (e => ele.entityName = e.getName)
      cascade foreach (c => collp.cascade = Some(c))
    }

    def cascade(c: String, orphanRemoval: Boolean = true): this.type = {
      this.cascade = Some(if (orphanRemoval && !c.contains("delete-orphan")) c + ",delete-orphan" else c)
      this
    }

    def cascaded: this.type = {
      this.cascade = Some("all,delete-orphan")
      this
    }
  }

  class OrderBy(orderBy: String) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      cast[CollectionProperty](property, holder, "order by should used on seq").orderBy = Some(orderBy)
    }
  }

  class Table(table: String) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      cast[CollectionProperty](property, holder, "table should used on seq").table = Some(table)
    }
  }

  class ColumnName(name: String) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      if (property.columns.size == 1) property.columns.head.name = name
    }
  }

  class OrderColumn(orderColumn: String) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      val collp = cast[SeqProperty](property, holder, "order column should used on many2many seq")
      val col = new Column(if (null != orderColumn) Mapping.OrderColumnName else orderColumn, false)
      collp.index = Some(new Index(col))
    }
  }

  class Length(len: Int) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      property.columns foreach (c => c.length = Some(len))
    }
  }

  class Target(clazz: Class[_]) extends Declaration {
    def apply(holder: EntityHolder[_], property: Property): Unit = {
      cast[ToOneProperty](property, holder, "target should used on manytoone").targetEntity = clazz.getName
    }
  }

  object Expression {
    // only apply unique on component properties
    def is(holder: EntityHolder[_], declarations: Seq[Declaration]): Unit = {
      val lasts = asScalaSet(holder.proxy.lastAccessed)
      if (!declarations.isEmpty && lasts.isEmpty) {
        throw new RuntimeException("Cannot find access properties for " + holder.entity.entityName + " with declarations:" + declarations)
      }
      lasts foreach { property =>
        val p = holder.entity.getProperty(property)
        declarations foreach (d => d(holder, p))
        p.mergeable = false
      }
      lasts.clear()
    }
  }
  class Expression(val holder: EntityHolder[_]) {

    def is(declarations: Declaration*): Unit = {
      Expression.is(holder, declarations)
    }

    def &(next: Expression): Expressions = {
      new Expressions(holder)
    }
  }

  class Expressions(val holder: EntityHolder[_]) {
    def &(next: Expression): this.type = {
      this
    }

    def are(declarations: Declaration*): Unit = {
      Expression.is(holder, declarations)
    }
  }

  final class EntityHolder[T](val entity: Entity, val binder: Binder, val clazz: Class[T], module: Mapping) {

    var proxy: Proxy.EntityProxy = _

    def cacheable(): this.type = {
      entity.cache(module.cacheConfig.region, module.cacheConfig.usage)
      this
    }

    def cache(region: String): this.type = {
      entity.cacheRegion = region
      this
    }

    def usage(usage: String): this.type = {
      entity.cacheUsage = usage
      this
    }

    def on(declarations: T => Any)(implicit manifest: Manifest[T]): this.type = {
      if (null == proxy) proxy = Proxy.generate(clazz)
      declarations(proxy.asInstanceOf[T])
      this
    }

    def generator(strategy: String): this.type = {
      entity.idGenerator = Some(new IdGenerator(strategy))
      this
    }

    def table(table: String): this.type = {
      entity.table = table
      this
    }
  }

  final class CacheConfig(var region: String = null, var usage: String = null) {
  }

  final class CacheHolder(val binder: Binder, val cacheRegion: String, val cacheUsage: String) {
    def add(first: List[Collection], definitionLists: List[Collection]*): this.type = {
      first.foreach(d => binder.addCollection(d.cache(cacheRegion, cacheUsage)))
      for (definitions <- definitionLists) {
        definitions.foreach(d => binder.addCollection(d.cache(cacheRegion, cacheUsage)))
      }
      this
    }

    def add(first: Class[_ <: org.beangle.commons.model.Entity[_]], classes: Class[_ <: org.beangle.commons.model.Entity[_]]*): this.type = {
      binder.getEntity(first).cache(cacheRegion, cacheUsage)
      for (clazz <- classes)
        binder.getEntity(clazz).cache(cacheRegion, cacheUsage)
      this
    }
  }

  final class Entities(val entities: collection.mutable.Map[String, Entity], cacheConfig: CacheConfig) {
    def except(clazzes: Class[_]*): this.type = {
      clazzes foreach { c => entities -= c.getName }
      this
    }

    def cacheable(): Unit = {
      entities foreach { e =>
        e._2.cacheRegion = cacheConfig.region
        e._2.cacheUsage = cacheConfig.usage
      }
    }

    def cache(region: String): this.type = {
      entities foreach (e => e._2.cacheRegion = region)
      this
    }

    def usage(usage: String): this.type = {
      entities foreach (e => e._2.cacheUsage = usage)
      this
    }
  }

  def columnName(propertyName: String, key: Boolean = false): String = {
    val lastDot = propertyName.lastIndexOf(".")
    val columnName = if (lastDot == -1) s"@${propertyName}" else "@" + propertyName.substring(lastDot + 1)
    if (key) columnName + "Id" else columnName
  }

  private def mismatch(msg: String, e: Entity, p: Property): Unit = {
    throw new RuntimeException(msg + s",Not for ${e.entityName}.${p.name}(${p.getClass.getSimpleName}/${p.propertyType.getName})")
  }

  private def cast[T](p: Property, holder: EntityHolder[_], msg: String)(implicit manifest: Manifest[T]): T = {
    if (!manifest.runtimeClass.isAssignableFrom(p.getClass)) mismatch(msg, holder.entity, p)
    p.asInstanceOf[T]
  }
}

@beta
abstract class Mapping extends Logging {

  import Mapping._
  private var currentHolder: EntityHolder[_] = _
  private var defaultIdGenerator: Option[String] = None
  private val cacheConfig = new CacheConfig()

  private var entities = Collections.newMap[String, Entity]

  import scala.language.implicitConversions

  implicit def any2Expression(i: Any): Expression = {
    new Expression(currentHolder)
  }

  private var binder: Binder = _

  def binding(): Unit

  protected def declare[B](a: B*): Seq[B] = {
    a
  }

  protected def notnull = new NotNull

  protected def unique = new Unique

  protected def length(len: Int) = new Length(len)

  protected def cacheable: Cache = {
    new Cache(new CacheHolder(binder, cacheConfig.region, cacheConfig.usage))
  }

  protected def cacheable(region: String, usage: String): Cache = {
    new Cache(new CacheHolder(binder, region, usage))
  }

  protected def target[T](implicit manifest: Manifest[T]): Target = {
    new Target(manifest.runtimeClass)
  }

  protected def depends(clazz: Class[_], mappedBy: String): One2Many = {
    new One2Many(Some(clazz), mappedBy).cascaded
  }

  protected def depends(mappedBy: String): One2Many = {
    new One2Many(None, mappedBy).cascaded
  }

  protected def one2many(mappedBy: String): One2Many = {
    new One2Many(None, mappedBy)
  }

  protected def one2many(clazz: Class[_], mappedBy: String): One2Many = {
    new One2Many(Some(clazz), mappedBy)
  }

  protected def orderby(orderby: String): OrderBy = {
    new OrderBy(orderby)
  }

  protected def table(t: String): Table = {
    new Table(t)
  }

  protected def ordered: OrderColumn = {
    new OrderColumn(null)
  }

  protected def ordered(column: String): OrderColumn = {
    new OrderColumn(column)
  }

  protected def column(name: String): ColumnName = {
    new ColumnName(name)
  }

  protected def eleColumn(name: String): ElementColumn = {
    new ElementColumn(name)
  }

  protected def eleLength(len: Int): ElementLength = {
    new ElementLength(len)
  }

  protected def typeis(t: String): TypeSetter = {
    new TypeSetter(t)
  }

  protected final def bind[T: ClassTag]()(implicit manifest: Manifest[T], ttag: ru.TypeTag[T]): EntityHolder[T] = {
    bind(manifest.runtimeClass.asInstanceOf[Class[T]], null, ttag)
  }

  protected final def bind[T: ClassTag](entityName: String)(implicit manifest: Manifest[T], ttag: ru.TypeTag[T]): EntityHolder[T] = {
    bind(manifest.runtimeClass.asInstanceOf[Class[T]], entityName, ttag)
  }

  private def bind[T](cls: Class[T], entityName: String, ttag: ru.TypeTag[T]): EntityHolder[T] = {
    val entity = binder.autobind(cls, entityName, ttag.tpe)
    //find superclass's id generator
    var superCls: Class[_] = cls.getSuperclass
    while (null != superCls && superCls != classOf[Object]) {
      if (entities.contains(superCls.getName)) {
        entities(superCls.getName).idGenerator match {
          case Some(idg) =>
            entity.idGenerator = Some(idg); superCls = classOf[Object]
          case None =>
        }
      }
      superCls = superCls.getSuperclass
    }

    if (entity.idGenerator.isEmpty) {
      val unsaved = BeanInfos.get(cls, null).getPropertyType("id") match {
        case Some(idtype) => if (idtype.isPrimitive) "0" else "null"
        case None         => "null"
      }
      this.defaultIdGenerator foreach { a => entity.idGenerator = Some(new IdGenerator(a).unsaved(unsaved)) }
    }
    binder.addEntity(entity)
    val holder = new EntityHolder(entity, binder, cls, this)
    currentHolder = holder
    entities.put(entity.entityName, entity)
    holder
  }

  protected final def defaultIdGenerator(strategy: String): Unit = {
    defaultIdGenerator = Some(strategy)
  }

  protected final def cache(region: String): CacheHolder = {
    new CacheHolder(binder, region, cacheConfig.usage)
  }

  protected final def cache(): CacheHolder = {
    new CacheHolder(binder, cacheConfig.region, cacheConfig.usage)
  }

  protected final def all: Entities = {
    val newEntities = Collections.newMap[String, Entity]
    new Entities(newEntities ++ entities, cacheConfig)
  }

  protected final def collection[T](properties: String*)(implicit manifest: Manifest[T]): List[Collection] = {
    val definitions = new scala.collection.mutable.ListBuffer[Collection]
    val clazz = manifest.runtimeClass
    properties foreach (p => definitions += new Collection(clazz, p))
    definitions.toList
  }

  protected final def defaultCache(region: String, usage: String) {
    cacheConfig.region = region
    cacheConfig.usage = usage
  }

  final def configure(binder: Binder): Unit = {
    logger.info(s"Process ${getClass.getName}")
    this.binder = binder
    this.binding()
    entities.clear()
  }

  def typedef(name: String, clazz: String, params: Map[String, String] = Map.empty): Unit = {
    binder.addType(name, clazz, params)
  }

  def typedef(forClass: Class[_], clazz: String): Unit = {
    binder.addType(forClass.getName, clazz, Map.empty)
  }

  def typedef(forClass: Class[_], clazz: String, params: Map[String, String]): Unit = {
    binder.addType(forClass.getName, clazz, params)
  }
}
