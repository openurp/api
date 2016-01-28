/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import scala.collection.mutable.{ Buffer, ListBuffer }
import org.beangle.commons.collection.Collections
import org.beangle.data.model.{ Coded, IntId, LongId, Named, TemporalOn, Updated }
import org.beangle.data.model.annotation.code
import org.openurp.base.model.{ Calendar, Campus, Department, School, TimeSetting }
import org.openurp.edu.base.code.model.{ Education, StdLabel, StdType }
import org.openurp.base.model.Room
/**
 * 项目
 *
 * @has 1..* AssignedTo 1..* Department
 * @has 1..* AssignedTo 1..* Education
 * @has 1..* AssignedTo 1..* StudentType
 * @has 1..* AssignedTo 1..* StdLabel
 * @depend - - - Calendar
 */
class Project extends IntId with Coded with TemporalOn with Updated with Named {
  /** 适用学校 */
  var school: School = _
  /** 校区列表 */
  var campuses: Buffer[Campus] = new ListBuffer[Campus]
  /** 部门列表 */
  var departments: Buffer[Department] = new ListBuffer[Department]
  /** 学历层次列表 */
  var educations: Buffer[Education] = new ListBuffer[Education]
  /** 学生分类列表 */
  var stdLabels: Buffer[StdLabel] = new ListBuffer[StdLabel]
  /**学生类别*/
  var stdTypes: Buffer[StdType] = new ListBuffer[StdType]
  /** 使用校历 */
  var calendar: Calendar = _
  /** 小节设置 */
  var timeSettings = Collections.newBuffer[TimeSetting]
  /** 描述 */
  var description: String = _
  /** 是否辅修 */
  var minor: Boolean = _
  /**项目属性*/
  var properties = Collections.newMap[String, String]

}
/**
 * 项目基础代码配置
 * 表示项目使用了基础代码集合中的哪些基础代码
 *
 * @author chaostone
 */
class ProjectCode extends LongId {
  /**项目*/
  var project: Project = _
  /**代码元*/
  var className: String = _
  /**代码IDs*/
  var codeIds: String = _
}
