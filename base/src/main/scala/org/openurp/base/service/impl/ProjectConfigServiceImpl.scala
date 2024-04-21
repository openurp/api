/*
 * Copyright (C) 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openurp.base.service.impl

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.model.{Project, ProjectProperty}
import org.openurp.base.service.{Feature, ProjectConfigService}

import scala.reflect.ClassTag

class ProjectConfigServiceImpl extends ProjectConfigService {

  var entityDao: EntityDao = _

  override def get[T](project: Project, name: String, defaultValue: T): T = {
    val query = OqlBuilder.from(classOf[ProjectProperty], "pp")
    query.where("pp.project=:project", project)
    query.where("pp.name=:name", name)
    query.cacheable()
    entityDao.search(query).headOption match {
      case Some(p) => Feature.convert(p.value, p.typeName).asInstanceOf[T]
      case None => defaultValue
    }
  }

  override def get[T: ClassTag](project: Project, f: Feature): T = {
    val query = OqlBuilder.from(classOf[ProjectProperty], "pp")
    query.where("pp.project=:project", project)
    query.where("pp.name=:name", f.name)
    query.cacheable()
    entityDao.search(query).headOption match {
      case Some(p) => Feature.convert(p.value, p.typeName).asInstanceOf[T]
      case None => Feature.convert(f.defaultValue, f.typeName).asInstanceOf[T]
    }
  }

  override def getInt(project: Project, f: Feature): Int = {
    get[Int](project, f)
  }

  override def getString(project: Project, f: Feature): String = {
    get[String](project, f)
  }

  override def getBoolean(project: Project, f: Feature): Boolean = {
    get[Boolean](project, f)
  }

  override def getFloat(project: Project, f: Feature): Float = {
    get[Float](project, f)
  }

  override def getDouble(project: Project, f: Feature): Double = {
    get[Double](project, f)
  }
}
