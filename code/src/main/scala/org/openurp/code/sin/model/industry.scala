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

package org.openurp.code.sin.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean

/**
 * 刊物
 */
@code("industry")
class Publication extends CodeBean {

  var grade: PublicationGrade = _
}

/**
 * 刊物级别
 */
@code("industry")
class PublicationGrade extends CodeBean {

}

/**
 * 出版社
 */
@code("industry")
class Press extends CodeBean {
  var grade: PressGrade = _
}

/**
 * 出版社别
 * 参见教育部标准JY/T 1001 4.6.1
 */
@code("industry")
class PressGrade extends CodeBean

/**
 * 图书分类
 *
 * @see http://ztflh.com/
 */
@code("industry")
class BookCategory extends CodeBean

/**
 * 境外教材类别
 * 原版、影印、翻译
 *
 * @see http://ztflh.com/
 */
@code("industry")
class ForeignBookType extends CodeBean

/**
 * 教材形态
 * 纸质、数字化、立体化、其他
 *
 * @see http://ztflh.com/
 */
@code("industry")
class TextbookForm extends CodeBean
