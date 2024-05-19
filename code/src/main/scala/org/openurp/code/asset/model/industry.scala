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

package org.openurp.code.asset.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean

/**
 * 房间类型
 * 参考教育部标准JY/T 1001 4.7.5
 *
 * @author chaostone
 * @since 2011-7-14
 * @since 3.0.0
 */
@code("industry")
class RoomType extends CodeBean {

}

/**
 * 建筑物用途类型
 * 参考教育部标准JY/T 1001 4.7.20
 *
 * @since 2016-07-03
 */
@code("industry")
class BuildingType extends CodeBean {

}

object DeviceType {
  /** 摄像头 */
  val Camera = 60101
}

/**
 * 教学仪器设备产品（物资）分类
 * 参考教育部标准JY/T 1001-2012
 *
 * @since 2016-07-03
 */
@code("industry")
class DeviceType extends CodeBean {

}
/**
 * 教室类型
 * 参见教育部标准JY/T 1001 4.3.3
 */
@code("industry")
class ClassroomType extends CodeBean
