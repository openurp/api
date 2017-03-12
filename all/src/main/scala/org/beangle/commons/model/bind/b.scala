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

import java.lang.reflect.Method

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.{ ClassLoaders, Primitives }
import org.beangle.commons.lang.reflect.BeanInfos
import org.beangle.commons.lang.time.Stopwatch
import org.beangle.commons.logging.Logging
import org.beangle.commons.model.bind.Jpas.isComponent
import org.beangle.commons.model.bind.Proxy.{ ComponentProxy, EntityProxy, ModelProxy }

import javassist.{ ClassPool, CtConstructor, CtField, CtMethod, LoaderClassPath }
import javassist.compiler.Javac

/**
 * @author chaostone
 */
object Proxy extends Logging {

  trait ModelProxy {
    def lastAccessed(): java.util.Set[String]
  }

  trait EntityProxy extends ModelProxy

  trait ComponentProxy extends ModelProxy {
    def setParent(proxy: ModelProxy, path: String): Unit
  }

  private val proxies = new collection.mutable.HashMap[String, Class[_]]
  private var pool = ClassPool.getDefault
  pool.appendClassPath(new LoaderClassPath(ClassLoaders.defaultClassLoader))

  def generate(clazz: Class[_]): EntityProxy = {
    val proxyClassName = clazz.getSimpleName + "_proxy"
    val classFullName = clazz.getName + "_proxy"
    val exised = proxies.getOrElse(classFullName, null)
    if (null != exised) return exised.newInstance().asInstanceOf[EntityProxy]

    val watch = new Stopwatch(true)
    val cct = pool.makeClass(classFullName)
    if (clazz.isInterface) cct.addInterface(pool.get(clazz.getName))
    else cct.setSuperclass(pool.get(clazz.getName))
    cct.addInterface(pool.get(classOf[EntityProxy].getName))
    val javac = new Javac(cct)
    cct.addField(javac.compile("public java.util.Set _lastAccessed;").asInstanceOf[CtField])

    val manifest = BeanInfos.get(clazz)
    val componentTypes = Collections.newMap[String, Class[_]]
    manifest.properties foreach {
      case (name, p) =>
        if (p.readable) {
          val getter = p.getter.get
          val value = if (p.typeinfo.optional) "null" else Primitives.defaultLiteral(p.clazz)
          val body = if (p.typeinfo.optional) {
            s"public scala.Option ${getter.getName}() { return $value;}"
          } else {
            s"public ${p.clazz.getName} ${getter.getName}() { return $value;}"
          }
          val ctmod = javac.compile(body).asInstanceOf[CtMethod]
          if (isComponent(p.clazz)) {
            componentTypes += (name -> generateComponent(p.clazz, name + "."))
            ctmod.setBody("{return super." + getter.getName + "();}")
          } else {
            ctmod.setBody("{_lastAccessed.add( \"" + name + "\");return " + value + ";}")
          }
          cct.addMethod(ctmod)
        }
    }
    val ctor = javac.compile("public " + proxyClassName + "(){}").asInstanceOf[CtConstructor]
    val ctorBody = new StringBuilder("{ _lastAccessed = new java.util.HashSet();")
    componentTypes foreach {
      case (name, componentClass) =>
        val p = manifest.properties(name)
        val setName = p.setter.get.getName
        val getName = p.getter.get.getName
        ctorBody ++= (setName + "(new " + componentClass.getName + "()); ((" + componentClass.getName + ")" + getName + "()).setParent(this," + "\"" + name + ".\");")
    }
    ctorBody ++= ("}")
    ctor.setBody(ctorBody.toString)
    cct.addConstructor(ctor)

    val ctmod = javac.compile("public java.util.Set lastAccessed() { return null;}").asInstanceOf[CtMethod]
    ctmod.setBody("{return _lastAccessed;}")
    cct.addMethod(ctmod)
    val maked = cct.toClass
    val proxy = maked.getConstructor().newInstance().asInstanceOf[EntityProxy]
    logger.debug(s"generate $classFullName using $watch")
    // cct.debugWriteFile("/tmp/model/")
    proxies.put(classFullName, proxy.getClass)
    proxy
  }

  private def generateComponent(clazz: Class[_], path: String): Class[_] = {
    val proxyClassName = clazz.getSimpleName + "_proxy"
    val classFullName = clazz.getName + "_proxy"
    val exised = proxies.getOrElse(classFullName, null)
    if (null != exised) return exised

    val cct = pool.makeClass(classFullName)
    if (clazz.isInterface()) cct.addInterface(pool.get(clazz.getName))
    else cct.setSuperclass(pool.get(clazz.getName))
    cct.addInterface(pool.get(classOf[ComponentProxy].getName))
    val javac = new Javac(cct)

    cct.addField(javac.compile("public " + classOf[ModelProxy].getName + " _parent;").asInstanceOf[CtField])
    cct.addField(javac.compile("public java.lang.String _path=null;").asInstanceOf[CtField])

    val manifest = BeanInfos.get(clazz)
    val componentTypes = Collections.newMap[String, Class[_]]
    manifest.properties foreach {
      case (name, p) =>
        if (p.readable) {
          val getter = p.getter.get
          val value = if (p.typeinfo.optional) "null" else Primitives.defaultLiteral(p.clazz)
          val body =
            if (p.typeinfo.optional) {
              s"public scala.Option ${getter.getName}() { return $value;}"
            } else {
              s"public ${p.clazz.getName} ${getter.getName}() { return $value;}"
            }
          val ctmod = javac.compile(body).asInstanceOf[CtMethod]
          val accessed = "_parent.lastAccessed()"
          if (isComponent(p.clazz)) {
            componentTypes += (name -> generateComponent(p.clazz, path + name + "."))
            ctmod.setBody("{" + accessed + ".add(_path + \"" + name + "\");return super." + getter.getName + "();}")
          } else {
            val value = Primitives.defaultLiteral(p.clazz)
            ctmod.setBody("{" + accessed + ".add(_path + \"" + name + "\");return " + value + ";}")
          }
          cct.addMethod(ctmod)
        }
    }
    val ctor = javac.compile("public " + proxyClassName + "(){}").asInstanceOf[CtConstructor]
    ctor.setBody("{this._parent=null;this._path=null;}")
    cct.addConstructor(ctor)

    //implement setParent and lastAccessed
    var ctmod = javac.compile("public void setParent(" + classOf[ModelProxy].getName + " proxy,String path) { return null;}").asInstanceOf[CtMethod]
    val setParentBody = new StringBuilder("{this._parent=$1;this._path=$2;")
    componentTypes foreach {
      case (name, componentClass) =>
        val p = manifest.properties(name)
        val setName = p.setter.get.getName
        val getName = p.getter.get.getName
        setParentBody ++= (setName + "(new " + componentClass.getName + "()); ((" + componentClass.getName + ")" + getName + "()).setParent(this," + "\"" + path + name + ".\");")
    }
    setParentBody ++= "}"
    ctmod.setBody(setParentBody.toString)

    cct.addMethod(ctmod)
    ctmod = javac.compile("public java.util.Set lastAccessed() { return null;}").asInstanceOf[CtMethod]
    ctmod.setBody("{return _parent.lastAccessed();}")
    cct.addMethod(ctmod)

    val maked = cct.toClass()
    // cct.debugWriteFile("/tmp/model/")
    proxies.put(classFullName, maked)
    maked
  }
}
