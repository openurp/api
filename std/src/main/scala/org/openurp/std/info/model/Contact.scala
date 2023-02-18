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

package org.openurp.std.info.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.std.model.Student

/**
 * 联系信息
 */
class Contact extends LongId with Updated{

  /**学生*/
  var std: Student = _

  /** 电子邮箱 */
  var email: Option[String] = None

  /** 电话 */
  var phone: Option[String] = None

  /** 移动电话 */
  var mobile: Option[String] = None

  /** 地址 入校后联系地址 */
  var address: Option[String] = None
}
