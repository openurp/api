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

package org.openurp.code.geo.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean

/** 国家地区
 * 参见国家推荐标准 GB/T 2659-2000
 *
 * @see http://en.wikipedia.org/wiki/ISO_3166-1
 * @see http://wenku.baidu.com/view/bd105c235901020207409cd1.html
 */
@code("nation")
class Country extends CodeBean {
  var alpha3Code: String = _
  var alpha2Code: String = _
  var shortName: String = _
}

/** 行政区划
 * 参见国家推荐标准 GB/T 2260-1999
 *
 * @see http://www.gfjl.org/thread-83266-1-1.html
 */
@code("nation")
class Division extends CodeBean {
  /** 父级 */
  var parent: Option[Division] = None

  var children = Collections.newBuffer[Division]
}
