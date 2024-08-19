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

import org.beangle.commons.lang.Strings
import org.beangle.data.dao.EntityDao
import org.openurp.base.model.{Department, School}
import org.openurp.base.service.DepartmentService

class DepartmentServiceImpl extends DepartmentService {

  var entityDao: EntityDao = _

  def reindex(school: School): Unit = {
    val departs = entityDao.findBy(classOf[Department], "school", school)
    val roots = departs.filter(_.parent.isEmpty).sortBy(_.code).toBuffer
    var index = 1
    val len = String.valueOf(roots.length).length
    roots foreach { root =>
      root.indexno = Strings.leftPad(index.toString, len, '0')
      initDepartIndexno(root, root.indexno + ".")
      index += 1
    }
    entityDao.saveOrUpdate(departs)
  }

  private def initDepartIndexno(depart: Department, prefix: String): Unit = {
    var i = 1
    val len = String.valueOf(depart.children.length).length
    depart.children.sortBy(_.code) foreach { d =>
      d.indexno = prefix + Strings.leftPad(i.toString, len, '0')
      initDepartIndexno(d, d.indexno + ".")
      i += 1
    }
  }
}
