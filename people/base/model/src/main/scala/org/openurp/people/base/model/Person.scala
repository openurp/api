/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
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
package org.openurp.people.base.model

import org.beangle.data.model.{ Coded, LongId, Updated }
import org.openurp.code.edu.model.Language
import org.openurp.code.geo.model.{ Country, Division }
import org.openurp.code.person.model.{ CompatriotType, Gender, IdType, MaritalStatus, Nation, PoliticalStatus, Religion }

/**
 * 通用人员信息
 */
class Person extends LongId with Updated with Coded {

  /**身份证件类型 */
  var idType: IdType = _

  /**姓名*/
  var name: Name = new Name

  /**姓名拼音 */
  var phoneticName: String = _

  /**曾用名 */
  var formerName: String = _

  /**性别*/
  var gender: Gender = _

  /**出生日期 */
  var birthday: java.sql.Date = _

  /**出生地*/
  var birthplace: Division = _

  /**籍贯 */
  var homeTown: String = _

  /**民族 */
  var nation: Nation = _

  /**国籍/地区 */
  var country: Country = _

  /**首要使用语言*/
  var language: Language = _

  /**婚姻状况 */
  var maritalStatus: MaritalStatus = _

  /**港澳台侨外 */
  var compatriotType: CompatriotType = _

  /**政治面貌 */
  var politicalStatus: PoliticalStatus = _

  /**宗教信仰 */
  var religion: Religion = _

}
