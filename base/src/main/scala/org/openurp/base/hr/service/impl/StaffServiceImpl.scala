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

package org.openurp.base.hr.service.impl

import org.beangle.commons.logging.Logging
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.hr.model.{Secretary, Staff, Teacher}
import org.openurp.base.hr.service.StaffService
import org.openurp.base.service.UserRepo

class StaffServiceImpl extends Logging, StaffService {

  var entityDao: EntityDao = _
  var userRepo: UserRepo = _

  override def createActiveUsers(): Unit = {
    val query = OqlBuilder.from(classOf[Teacher], "teacher")
    query.where("teacher.endOn is null")
    val teachers = entityDao.search(query)
    teachers.foreach { teacher =>
      userRepo.createUser(teacher)
    }

    val query2 = OqlBuilder.from(classOf[Staff], "staff")
    query2.where("staff.endOn is null")
    query2.where(s"not exists(from ${classOf[Teacher].getName} t where t.staff=staff)")
    val staffs = entityDao.search(query2)
    staffs.foreach { staff =>
      userRepo.createUser(staff, None)
    }

    val query3 = OqlBuilder.from(classOf[Secretary], "s")
    val secretaries = entityDao.search(query3)
    secretaries.foreach { secretary =>
      userRepo.createUser(secretary)
    }
  }
}
