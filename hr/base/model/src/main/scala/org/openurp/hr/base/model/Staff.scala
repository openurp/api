/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.hr.base.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.{ Coded, LongId, Updated }
import org.openurp.code.hr.model.StaffType
import org.openurp.people.base.model.Person

/**
 * 教职工信息
 */
class Staff extends LongId with Updated with Coded {

  /**人员信息*/
  var person: Person = _

  /**状态*/
  var state: StaffState = _

  /**状态日志*/
  var states = Collections.newBuffer[StaffState]

  /**教职工类别*/
  var staffType: StaffType = _

  /**职称信息*/
  var titleInfo: TitleInfo = _

  /**导师信息*/
  var tutorInfo: TutorInfo = _

  /**是否在编*/
  var registed: Boolean = _

}
