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

package org.openurp.edu.course.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{TemporalOn, Updated}
import org.openurp.base.edu.model.*
import org.openurp.base.model.*
import org.openurp.code.edu.model.*

import java.time.Instant
import java.util.Locale

/** 课程教学大纲
 *
 */
class Syllabus extends LongId with Updated with TemporalOn {

  /** 课程 */
  var course: Course = _

  /** 语种 */
  var locale: Locale = _

  /** 简介 */
  var description: String = _

  /** 生效学年学期 */
  var semester: Semester = _

  //object targets
  /** 面向的培养层次 */
  var levels = Collections.newSet[EducationLevel]

  /** 面向的专业 */
  var majors = Collections.newSet[Major]

  /** 分类课时 */
  var hours = Collections.newBuffer[SyllabusCreditHour]

  /** 教学方式 */
  var methods = Collections.newSet[TeachingMethod]

  //course natures
  /** 学期中的开课阶段 */
  var stage: Option[CalendarStage] = None

  /** 课程模块 */
  var module: CourseModule = _

  /** 必修选修 */
  var rank: CourseRank = _

  /** 课程性质 */
  var nature: CourseNature = _

  /** 考试方式 */
  var examMode: ExamMode = _

  /** 计分方式 */
  var gradingMode: GradingMode = _

  // other course relations
  /** 先修课程 */
  var prerequisites: Option[String] = None

  /** 并修课程 */
  var corequisites: Option[String] = None

  /** 后续课程 */
  var subsequents: Option[String] = None

  //obe
  /** 课程目标 */
  var objectives = Collections.newBuffer[SyllabusObjective]

  /** 对毕业要求的支撑 */
  var outcomes = Collections.newBuffer[SyllabusOutcome]

  //topic and lessons
  /** 教学内容 */
  var topics = Collections.newBuffer[SyllabusTopic]

  /** 考核百分比 */
  var percents = Collections.newBuffer[SyllabusAssessPercent]

  //textbooks and resources
  /** 教材和参考书目 */
  var textbooks = Collections.newBuffer[Textbook]

  /** 参考数目 */
  var bibliography: Option[String] = None

  /** 其他教学资源 */
  var materials: Option[String] = None

  /** 课程网站地址 */
  var website: Option[String] = None

  //text sections
  var texts = Collections.newBuffer[SyllabusText]

  //admin and audit infoes
  /** 开课院系 */
  var department: Department = _

  /** 教研室 */
  var office: Option[TeachingOffice] = None

  /** 课程负责人 */
  var director: Option[User] = None

  /** 作者 */
  var writer: User = _

  /** 状态 */
  var status: AuditStatus = AuditStatus.Draft

  /** 审核人 */
  var auditor: Option[User] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None
}
