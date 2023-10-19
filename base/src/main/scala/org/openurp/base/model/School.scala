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

package org.openurp.base.model

import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.*
import org.openurp.code.edu.model.{Institution, InstitutionCategory}
import org.openurp.code.geo.model.Division

/**
 * 学校
 */
class School extends IntId with Coded with Named with TemporalOn {
  /** 机构 */
  var institution: Institution = _
  /** 性质类别 */
  var category: InstitutionCategory = _
  /** 省份 */
  var division: Division = _
  /** logo 地址 */
  var logoUrl: String = _
  /** 简称 */
  var shortName: Option[String] = None
  /** 标识码(10位) */
  var identifier: Option[String] = None
  /** 主管部门 */
  var superiorOrg: Option[String] = None
  /** 统一信用代码（unified social credit code) */
  var uscc: Option[String] = None
}
