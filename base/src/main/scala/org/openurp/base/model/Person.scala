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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Updated}
import org.openurp.code.geo.model.Country
import org.openurp.code.person.model.*

import java.time.LocalDate

/**
 * 通用自然人信息
 */
class Person extends LongId with Updated with Coded {

  /** 身份证件类型 */
  var idType: IdType = _

  /** 姓名 */
  var name: Name = new Name

  /** 姓名拼音 */
  var phoneticName: Option[String] = None

  /** 曾用名 */
  var formerName: Option[String] = None

  /** 性别 */
  var gender: Gender = _

  /** 出生日期 */
  var birthday: Option[LocalDate] = None

  /** 出生地 */
  var birthplace: Option[String] = None

  /** 籍贯 */
  var homeTown: Option[String] = None

  /** 民族 */
  var nation: Option[Nation] = None

  /** 政治面貌 */
  var politicalStatus: Option[PoliticalStatus] = None

  /** 国籍/地区 */
  var country: Option[Country] = None

  /** 首要使用语言 */
  var language: Option[Language] = None

  /** 港澳台侨外 */
  var compatriotType: Option[CompatriotType] = None

  /** 宗教信仰 */
  var religion: Option[Religion] = None

}

import org.beangle.data.model.Component

/**
 * 姓名
 *
 * @see http://www.w3.org/International/questions/qa-personal-names
 * @see http://www.wikitree.com/wiki/Name_Fields
 */
class Name extends Component {

  /** 名 */
  var givenName: Option[String] = None

  /** 中间名 */
  var middleName: Option[String] = None

  /** 姓 */
  var familyName: Option[String] = None

  /** 姓名 */
  var formattedName: String = _

  override def toString: String = {
    formattedName
  }
}
