/*
 * Copyright (C) 2005, The OpenURP Software.
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

enum SyllabusStatus(val name: String) {
  case Draft extends SyllabusStatus("草稿")
  case Submited extends SyllabusStatus("已提交")
  case Unpassed extends SyllabusStatus("未通过")
  case Passed extends SyllabusStatus("审核通过")
  case Published extends SyllabusStatus("已发布")
}
