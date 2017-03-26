/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import org.beangle.commons.model.IntId
import org.openurp.base.model.Campus
import org.openurp.edu.base.code.model.Education
import org.beangle.commons.model.LongId

/**
 * 专业培养方案每学期对应校区
 */
class TermCampus extends LongId {

  var program: Program = _

  /** 对应学期 */
  var terms: Terms = _

  /** 校区信息 */
  var campus: Campus = _

}
