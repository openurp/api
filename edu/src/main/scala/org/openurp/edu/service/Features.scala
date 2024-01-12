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

import org.openurp.base.service.{Feature, FeatureScope}

object Features {

  object Program extends FeatureScope {

    override def namespace: String = "edu.program"

    /** 是否在计划预览界面链接课程简介(默认false) */
    val LinkCourseEnabled = Feature(this, "link_course_info_enabled", "是否在计划预览界面链接课程简介", false)

    /** 是否支持培养计划中的学位绩点(默认false) */
    val DegreeGpaSupported = Feature(this, "degree_gpa_supported", "是否支持培养计划中的学位绩点", false)

    /** 是否支持培养计划中的学位课程(默认false) */
    val DegreeCourseSupported = Feature(this, "degree_course_supported", "是否支持培养计划中的学位课程", false)

    /** 培养方案是否显示课时(默认true) */
    val DisplayCreditHour = Feature(this, "display_credit_hour", "培养方案是否显示课时", true)

    /** 是否向学生展示专业替代课程(默认true) */
    val MajorAlternativeShow2Std = Feature(this, "major_alternative.show2std", "是否向学生展示专业替代课程", true)

    /** 是否允许申请课程替代时选择多对一替代 */
    val AlternativeApplyMultipleEnabled = Feature(this, "alternative_apply_multiple_enabled", "是否允许申请课程替代时选择多对一替代", false)

  }

  object Clazz extends FeatureScope {
    override def namespace: String = "edu.clazz"

    /** 课表样式 */
    val TableStyle = Feature(this, "tablestyle", "课表样式", List("WEEK_TABLE", "UNIT_COLUMN", "LIST"))

    /** 是否显示单个教学任务的index页面 */
    val IndexSupported = Feature(this, "show_index", "是否显示单个教学任务的index页面", false)
  }

  object Grade extends FeatureScope {
    override def namespace: String = "edu.grade"

    /** 缓考成绩直接作为最终成绩 */
    val DelayIsGa = Feature(this, "delay_is_ga", "缓考成绩直接作为最终成绩", false)

    /** 补考成绩直接作为最终成绩 */
    val MakeupIsGa = Feature(this, "makeup_is_ga", "补考成绩直接作为最终成绩", false)

    /** 默认成绩精确度(默认0) */
    val ScorePrecision = Feature(this, "score_precision", "默认成绩精确度", 0)

    /** 学生成绩显示界面的默认风格(默认normal,[normal,simple]) */
    val StdPageStyle = Feature(this, "std_page_style", "学生成绩显示界面的默认风格", List("normal", "simple"))

    /** 教师界面成绩是否需要录入两次 */
    val InputTwice = Feature(this, "input_twice", "输入成绩时是否需要输入两次", false)
  }

  object Exam extends FeatureScope {
    override def namespace: String = "edu.exam"

    /** 是否按照院系统一代码集中考试(默认false) */
    val CertralizedByDepart = Feature(this, "centralized_by_depart", "是否按照院系统一代码集中考试", false)

    /** 是否向学生展示考场座位号(默认true) */
    val ShowStdSeatNo = Feature(this, "show_std_seat_no", "是否向学生展示考场座位号", true)

    /** 是否允许网上申请缓考 */
    val DeferApplyEnabled = Feature(this, "defer_apply_enabled", "是否允许网上申请缓考", false)
  }

  object Exempt extends FeatureScope {
    override def namespace: String = "edu.exempt"

    val ScoreNeeded = Feature(this, "score_needed", "交换成绩免修时是否需要填写课程成绩", true)
  }
}
