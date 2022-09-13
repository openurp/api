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

package org.openurp.base

object Features {
  /** 每学分对应学时数 */
  val EduCourseHoursPerCredit = "edu.course.hours_per_credit"

  /** 课程是否支持不同层次学分不同 */
  val EduCourseLevelCreditSupported = "edu.course.level_credit_supported"

  /** 学籍信息是否支持分班管理 */
  val StdInfoSquadSupported = "std.info.squad_supported"

  /** 学籍信息中是否支持导师 */
  val StdInfoTutorSupported = "std.info.tutor_supported"
}
