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

package org.openurp.edu.grade.model

/** 课程未通过时的后续途径
 */
enum CoursePendingWay(val id: Int, val name: String) {
  /** 有在读的修读记录，尚未出成绩 */
  case Taking extends CoursePendingWay(1, "在读")

  /** 有补缓考记录，尚未出成绩 */
  case Makeup extends CoursePendingWay(2, "补缓考")

  /** 毕业学年课程，尚未出成绩也预计能完成 */
  case GraduateYear extends CoursePendingWay(3, "毕业学年课程")
}
