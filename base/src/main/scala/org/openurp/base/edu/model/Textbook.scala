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

package org.openurp.base.edu.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Named, Remark, TemporalOn}
import org.openurp.base.model.Project
import org.openurp.code.edu.model.{BookAwardType, BookType, DisciplineCategory}
import org.openurp.code.sin.model.{BookCategory, ForeignBookType, Press, TextbookForm}

import java.time.LocalDate

/**
 * 教材基本信息
 */
class Textbook extends LongId, TemporalOn, Named, Remark {
  var project: Project = _
  /** ISBN */
  var isbn: Option[String] = None
  /** 编著者 */
  var author: String = _
  /** 译作者 */
  var translator: Option[String] = None
  /** 出版社 */
  var press: Option[Press] = None
  /** 版次 */
  var edition: String = _
  /** 价格 */
  var price: Option[Float] = None
  /** 简介 */
  var description: Option[String] = None
  /** 教材类型 */
  var bookType: Option[BookType] = None
  /** 图书分类 */
  var category: Option[BookCategory] = None
  /** 出版日期 */
  var publishedOn: LocalDate = _
  /** 获奖类型 */
  var awardType: Option[BookAwardType] = None
  /** 颁发单位 */
  var awardOrg: Option[String] = None
  /** 图书系列 */
  var series: Option[String] = None
  /** 校内编著 */
  var madeInSchool: Boolean = false
  /** 是否是境内教材 */
  var domestic: Boolean = true
  /** 境外教材类型 */
  var foreignBookType: Option[ForeignBookType] = None
  /** 学科门类 */
  var disciplineCategory: Option[DisciplineCategory] = None
  /** 教材形态 */
  var bookForm: Option[TextbookForm] = None
}
