/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.{ Component, LongId }
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.{ Coded, Remark, TemporalOn, Updated }
import org.openurp.base.model.{ Campus, Department, User }
import org.openurp.code.edu.model.{ StudentStatus, StudyType }
import org.openurp.edu.base.{ EduSpanBased, StdEnrollment }
import org.openurp.edu.base.code.model.{ EduSpan, StdLabel, StdLabelType, StdType }
import org.openurp.people.base.model.Person
import org.beangle.data.model.pojo.DateRange

/**
 * 学籍信息实现
 */
class Student extends LongId with EduSpanBased with Coded with Updated with Remark with DateRange {

  /**基本信息*/
  var person: Person = _

  /**用户信息*/
  var user: User = _

  /** 学生类别 所在项目内的学生类别 */
  var stdType: StdType = _

  /** 学籍状态 */
  var state: Option[StudentState] = None

  /** 状态 */
  var states = Collections.newBuffer[StudentState]

  /** 学生分类标签 */
  var labels = Collections.newMap[StdLabelType, StdLabel]

  /** 学制 学习年限（允许0.5年出现） */
  var duration: Float = _

  /** 是否有学籍 */
  var registed: Boolean = _

  /** 学习形式 全日制/业余/函授 */
  var studyType: StudyType = _

  /**导师*/
  var tutor: Option[Teacher] = None
}
/**
 * 学籍状态日志
 */

class StudentState extends LongId with StdEnrollment with TemporalOn with Remark {

  /** 学生 */
  var std: Student = _

  /** 年级 */
  var grade: String = _

  /** 管理院系 */
  var department: Department = _

  /** 专业 */
  var major: Major = _

  /**专业方向*/
  var direction: Option[Direction] = None

  /** 行政班级 */
  var squad: Option[Squad] = None

  /** 是否在校 */
  var inschool: Boolean = _

  /** 学籍状态 */
  var status: StudentStatus = _

  /** 校区 */
  var campus: Campus = _

  /**培养方案*/
  var program: Option[Program] = None
}

/**
 * 学生范围
 */
class StudentScope extends Component {
  /**年级*/
  var grades: String = _
  /**项目*/
  var project: Project = _
  /**培养层次集合*/
  var spans: collection.mutable.Set[EduSpan] = _
  /**学生类别集合*/
  var stdTypes: collection.mutable.Set[StdType] = _
  /**部门集合*/
  var departments: collection.mutable.Set[Department] = _
  /**专业集合*/
  var majors: collection.mutable.Set[Major] = _
  /**专业方向集合*/
  var directions: collection.mutable.Set[Direction] = _

}
