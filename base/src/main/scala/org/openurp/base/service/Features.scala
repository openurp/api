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

package org.openurp.base.service

object Features {

  object Std extends FeatureScope {
    override def namespace: String = "base.std"

    /** 学籍信息是否支持分班管理 */
    val SquadSupported = Feature(this, "squad_supported", "学籍信息是否支持分班管理", true)

    /** 学籍信息中是否支持导师 */
    val TutorSupported = Feature(this, "tutor_supported", "学籍信息中是否支持导师", false)

    /** 学籍信息钟是否支持学位论文导师 */
    val AdvisorSupported = Feature(this, "advisor_supported", "学籍信息钟是否支持学位论文导师", false)
  }

  object Edu extends FeatureScope {
    override def namespace: String = "base.edu"

    /** 每学分对应学时数 */
    val CourseHoursPerCredit = Feature(this, "course_hours_per_credit", "每学分对应学时数", 16)

    /** 课程是否支持不同层次学分不同 */
    val CourseLevelCreditSupported = Feature(this, "course_level_credit_supported", "课程是否支持不同层次学分不同", false)
  }

  object Hr extends FeatureScope {
    override def namespace: String = "base.hr"

    /** 教师的所在部门和任教部门完全一致 */
    val TeacherSameDepartWithStaff = Feature(this, "teacher_same_depart_with_staff", "教师的所在部门和任教部门完全一致", false)

    /** 教师信息上其他的必填属性 */
    var TeacherExtraRequiredProperties = Feature(this, "teacher_extra_required_properties", "教师信息上其他的必填属性", "")

    /** 教职工信息上其他的必填属性 */
    var StaffExtraRequiredProperties = Feature(this, "staff_extra_required_properties", "教职工信息上其他的必填属性", "")
  }
}
