/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2016, OpenURP Software.
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
package org.openurp.code.sin.model

import org.beangle.commons.model.annotation.code
import org.openurp.code.BaseCodeBean

/**
 * 刊物
 */
@code("industry")
class Publication extends BaseCodeBean {

  var grade: PublicationGrade = _
}

/**
 * 刊物级别
 */
@code("industry")
class PublicationGrade extends BaseCodeBean {

}

/**
 * 出版社
 */
@code("industry")
class Press extends BaseCodeBean{
   var grade: PressGrade = _
}

/**
 * 出版社别
 * 参见教育部标准JY/T 1001 4.6.1
 */
@code("industry")
class PressGrade extends BaseCodeBean
