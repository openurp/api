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

package org.openurp.edu.attendance.model

enum AttendState(val id: Int, val name: String, val absent: Boolean) {
  case None extends AttendState(0, "未考勤", true)
  case Present extends AttendState(1, "出勤", false)
  case Absent extends AttendState(2, "旷课", true)
  case Leave extends AttendState(3, "请假", true)
  case Later extends AttendState(4, "迟到", false)
  case LeaveEarly extends AttendState(5, "早退", false)

  override def toString: String = name
}
