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

package org.openurp.edu.service

import org.openurp.base.service.Feature

object Features {

  /** 是否在计划预览界面链接课程简介(默认false) */
  val ProgramLinkCourseEnabled = Feature("edu.program.link_course_info_enabled", "是否在计划预览界面链接课程简介", false)

  /** 是否支持培养计划中的学位绩点(默认false) */
  val ProgramDegreeGpaSupported = Feature("edu.program.degree_gpa_supported", "是否支持培养计划中的学位绩点", false)

  /** 是否支持培养计划中的学位课程(默认false) */
  val ProgramDegreeCourseSupported = Feature("edu.program.degree_course_supported", "是否支持培养计划中的学位课程", false)

  /** 培养方案是否显示课时(默认true) */
  val ProgramDisplayCreditHour = Feature("edu.program.display_credit_hour", "培养方案是否显示课时", true)

  /** 是否向学生展示专业替代课程(默认true) */
  val ProgramMajorAlternativeShow2Std = Feature("edu.program.major_alternative.show2std", "是否向学生展示专业替代课程", true)

  /** 是否允许申请课程替代时选择多对一替代 */
  val ProgramAlternativeApplyMultipleEnabled = Feature("edu.program.alternative_apply_multiple_enabled", "是否允许申请课程替代时选择多对一替代", false)

  /** 课表样式 */
  val ClazzTableStyle = Feature("edu.clazz.tablestyle", "课表样式", List("WEEK_TABLE", "UNIT_COLUMN", "LIST"))

  /** 是否显示单个教学任务的index页面 */
  val ClazzIndexSupported = Feature("edu.clazz.show_index", "是否显示单个教学任务的index页面", false)

  /** 默认成绩精确度(默认0) */
  val GradeScorePrecision = Feature("edu.grade.score_precision", "默认成绩精确度", 0)

  /** 学生成绩显示界面的默认风格(默认normal,[normal,simple]) */
  val GradeStdPageStyle = Feature("edu.grade.std_page_style", "学生成绩显示界面的默认风格", List("normal", "simple"))

  /** 是否按照院系统一代码集中考试(默认false) */
  val ExamCertralizedByDepart = Feature("edu.exam.centralized_by_depart", "是否按照院系统一代码集中考试", false)

  /** 是否向学生展示考场座位号(默认true) */
  val ExamShowStdSeatNo = Feature("edu.exam.show_std_seat_no", "是否向学生展示考场座位号", true)

}
