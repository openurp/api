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

package org.openurp.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.*
import org.openurp.code.hr.model.UserCategory
import org.openurp.code.person.model.Gender

/**
 * 通用人员信息
 */
class User extends LongId, Coded, Named, EnNamed, Updated, Remark, TemporalOn {

  var school: School = _

  var gender: Gender = _

  var department: Department = _

  var category: UserCategory = _

  var email: Option[String] = None

  var mobile: Option[String] = None

  def description: String = {
    s"$code $name ${department.shortName.getOrElse(department.name)}"
  }
}
