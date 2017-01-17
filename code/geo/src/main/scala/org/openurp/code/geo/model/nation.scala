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
package org.openurp.code.geo.model

import org.beangle.commons.model.annotation.code
import org.openurp.code.BaseCodeBean
import org.beangle.commons.collection.Collections

/**
 * 国家地区
 * 参见国家推荐标准 GB/T 2659-2000
 * @see http://en.wikipedia.org/wiki/ISO_3166-1
 * @see http://wenku.baidu.com/view/bd105c235901020207409cd1.html
 */
@code("nation")
class Country extends BaseCodeBean {
  var alpha3Code: String = _
  var alpha2Code: String = _
  var shortName: String = _
}

/**
 * 行政区划
 * 参见国家推荐标准 GB/T 2260-1999
 * @see http://www.gfjl.org/thread-83266-1-1.html
 */
@code("nation")
class Division extends BaseCodeBean {
  /** 父级 */
  var parent: Division = _

  var children = Collections.newBuffer[Division]
}
