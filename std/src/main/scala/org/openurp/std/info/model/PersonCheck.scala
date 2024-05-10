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

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.Objects
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.std.model.Student

/** 学生信息确认 */
class PersonCheck extends LongId, Updated {

  var std: Student = _

  /** 变更项目 */
  var changes = Collections.newBuffer[PersonCheckItem]

  /** 确认信息明细 */
  var details: String = _

  /** 移动电话 */
  var mobile: Option[String] = None

  /** 附件 */
  var attachment: Option[String] = None

  /** 是否确认 */
  var confirmed: Option[Boolean] = None

  /** 审核意见 */
  var auditOpinion: Option[String] = None

  def this(std: Student) = {
    this()
    this.std = std
  }

  def addChange(field: PersonField, oldValue: String, newValue: String): Option[PersonCheckItem] = {
    if (details.nonEmpty) details += "\n"
    details += s"${field.name}:${newValue}"
    val exists = changes.find(_.field == field)
    val isSame = Objects.equals(oldValue, newValue)
    if (isSame) {
      if exists.nonEmpty then changes.subtractAll(exists)
      None
    } else {
      if exists.isEmpty then
        val change = new PersonCheckItem(this, field, oldValue, newValue)
        changes.addOne(change)
        Some(change)
      else
        exists.head.update(oldValue, newValue)
        exists
    }
  }

  def hasChange(fieldCode: String): Boolean = {
    changes.exists(_.field.code == fieldCode)
  }

  def getNewValue(fieldCode: String): Option[String] = {
    changes.find(_.field.code == fieldCode).map(_.newValue)
  }
}

enum PersonField(val id: Int, val code: String, val name: String) {
  case Name extends PersonField(1, "name", "姓名")
  case EnName extends PersonField(2, "enName", "姓名拼音")
  case Gender extends PersonField(3, "gender", "性别")
  case Birthday extends PersonField(4, "birthday", "出生日期")
  case HomeTown extends PersonField(5, "homeTown", "籍贯")
  case IdType extends PersonField(6, "idType", "证件类型")
  case IdCode extends PersonField(7, "code", "证件号码")
  case Nation extends PersonField(8, "nation", "民族")
  case PoliticalStatus extends PersonField(9, "politicalStatus", "政治面貌")
  case Country extends PersonField(10, "country", "国家地区")
}
