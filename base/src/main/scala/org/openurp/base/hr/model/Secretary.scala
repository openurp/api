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

package org.openurp.base.hr.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.base.model.Project

import scala.collection.mutable

/** 教学秘书
 */
class Secretary extends LongId, TemporalOn {

  /** 教职工 */
  var staff: Staff = _

  /** 办公电话 */
  var officePhone: Option[String] = None

  /** 办公室地址 */
  var officeAddr: Option[String] = None

  /** 办公邮件 */
  var officeEmail: Option[String] = None

  /** 项目列表 */
  var projects: mutable.Set[Project] = Collections.newSet[Project]
}
