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
  /** 教师的所在部门和任教部门完全一致 */
  val EduTeacherSameDepartWithStaff = Feature("edu.teacher.same_depart_with_staff", "教师的所在部门和任教部门完全一致", false)

  /** 每学分对应学时数 */
  val EduCourseHoursPerCredit = Feature("edu.course.hours_per_credit", "每学分对应学时数", 16)

  /** 课程是否支持不同层次学分不同 */
  val EduCourseLevelCreditSupported = Feature("edu.course.level_credit_supported", "课程是否支持不同层次学分不同", false)

  /** 学籍信息是否支持分班管理 */
  val StdInfoSquadSupported = Feature("std.info.squad_supported", "学籍信息是否支持分班管理", true)

  /** 学籍信息中是否支持导师 */
  val StdInfoTutorSupported = Feature("std.info.tutor_supported", "学籍信息中是否支持导师", false)

  /** 学籍信息钟是否支持学位论文导师 */
  val StdInfoAdvisorSupported = Feature("std.info.advisor_supported", "学籍信息钟是否支持学位论文导师", false)
}
