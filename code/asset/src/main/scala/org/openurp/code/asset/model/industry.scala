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
package org.openurp.code.asset.model

import org.openurp.code.BaseCodeBean
import org.beangle.data.model.annotation.code

/**
 * 房间类型
 * 参考教育部标准JY/T 1001 4.7.5
 * @author chaostone
 * @since 2011-7-14
 * @since 3.0.0
 */
@code("industry")
class RoomType extends BaseCodeBean {

}

/**
 * 建筑物用途类型
 * 参考教育部标准JY/T 1001 4.7.20
 * @since 2016-07-03
 */
@code("industry")
class BuildingType extends BaseCodeBean {

}
