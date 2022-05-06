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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Named, TemporalOn}
import org.openurp.base.edu.model.Major
import org.openurp.code.edu.model.{DisciplineCategory, Institution}

/**
 * 辅修专业
 */
class MinorMajor extends LongId with Coded with Named with TemporalOn {

  /** 教育机构 */
  var institution: Institution = _

  /** 英文名 */
  var enName: Option[String] = None

  /** 学科门类 */
  var category: DisciplineCategory = _

  /** 对应本校的专业 */
  var major: Option[Major] = None
}
