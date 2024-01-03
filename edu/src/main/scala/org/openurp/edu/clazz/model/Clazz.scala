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

package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.code.CourseType
import org.openurp.base.edu.model.Course
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.*
import org.openurp.code.edu.model.{ExamMode, TeachLangType}
import org.openurp.edu.clazz.code.ClazzTag

import scala.collection.mutable

/**
 * 教学任务 </p> 每学期开课任务，以此为开始作为排课、排考、成绩录入的依据。代表着从对上课对象和开课院系的完整的教学实际任务信息.
 * <li>1、教什么（课程名称、类别）、什么时候教（学年学期），谁来教（授课学院、零个或多个教师）</li>
 * <li>2、教学班信息——教谁（学生人数、上课对象（院系、学生类别）、选课对象）</li>
 * <li>3、安排情况（具体安排,开始周、结束周、总课时）</li>
 * <li>4、选课情况（选课上下限、实选人数、选课规则）</li>
 * <li>5、任务要求（教室要求、课程要求（教材、参考书、案例）、是否挂牌、是否双语）</li>
 * <li>6、创建时间、修改时间、备注</li>
 */
class Clazz extends LongId, ProjectBased, Updated, Cloneable, Remark {

  /** 课程序号 */
  var crn: String = _

  /** 课程 */
  var course: Course = _

  /** 主题 */
  var subject: Option[String] = None

  /** 课程类别 */
  var courseType: CourseType = _

  /** 开课院系 */
  var teachDepart: Department = _

  /** 授课教师 */
  var teachers: mutable.Buffer[Teacher] = Collections.newBuffer[Teacher]

  /** 开课校区 */
  var campus: Campus = _

  /** 教学班名称 */
  var clazzName: String = _

  /** 教学班 */
  var enrollment: Enrollment = _

  /** 教学日历 */
  var semester: Semester = _

  /** 课程安排 */
  var schedule: Schedule = _

  /** 考核方式 */
  var examMode: ExamMode = _

  /** 是否有补考 */
  var hasMakeup: Boolean = _

  /** 授课语言类型 */
  var langType: TeachLangType = _

  /** 所属课程组 */
  var group: Option[ClazzGroup] = None

  /** 审核状态 */
  var status: AuditStatus = _

  /** 任务标签 */
  var tags: mutable.Set[ClazzTag] = Collections.newSet[ClazzTag]

  /** 执行计划ID */
  var planId: Option[Long] = None

  def courseName: String = {
    subject match {
      case None => course.name
      case Some(s) => s"${course.name}($s)"
    }
  }
}
