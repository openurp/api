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
import org.beangle.data.model.pojo.Named
import org.openurp.base.std.model.Student
import org.openurp.code.person.model.{FamilyRelationship, IdType}

/**
 * 社会关系
 */
class SocialRelation extends LongId with Named {

  /** 学生 */
  var std: Student = _

  /** 关系 */
  var relationship: FamilyRelationship = _

  /** 单位、职务 */
  var duty: Option[String] = None

  /** 联系电话 */
  var phone: Option[String] = None

  /** 证件类型 */
  var idType: Option[IdType] = None

  /** 证件 */
  var idcard: Option[String] = None
}
