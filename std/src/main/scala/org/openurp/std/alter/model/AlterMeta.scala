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

package org.openurp.std.alter.model

import org.beangle.commons.lang.Strings

object AlterMeta {
  def of(shortName: String): AlterMeta = {
    AlterMeta.valueOf(Strings.capitalize(shortName))
  }
}

/**
 * 学籍异动属性
 */
enum AlterMeta(val id: Int, val name: String) {
  case Grade extends AlterMeta(1, "年级")
  case Department extends AlterMeta(2, "院系")
  case Major extends AlterMeta(3, "专业")
  case Direction extends AlterMeta(4, "专业方向")
  case Squad extends AlterMeta(5, "班级")
  case Inschool extends AlterMeta(6, "是否在校")
  case Status extends AlterMeta(7, "学籍状态")
  case Campus extends AlterMeta(8, "校区")
  case EndOn extends AlterMeta(9, "毕业日期")
  case Tutor extends AlterMeta(10, "导师")
  case Advisor extends AlterMeta(11, "学位论文指导教师")
  case GraduateOn extends AlterMeta(12, "预计毕业日期")

  override def toString: String = name

}
