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

package org.openurp.base.edu.model

enum BookAdoption(val id: Int, val name: String) {

  @deprecated
  case None extends BookAdoption(0, "使用参考资料")

  case UseTextBook extends BookAdoption(1, "使用教材")
  case UseLecture extends BookAdoption(2, "使用讲义")
  case UseRefer extends BookAdoption(3, "使用参考资料")

  override def toString: String = name
}
