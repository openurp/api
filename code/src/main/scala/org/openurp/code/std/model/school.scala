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

package org.openurp.code.std.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean

/**
 * 学生类别
 * 例如留学生、港澳台等
 */
@code("school")
class StdType extends CodeBean

/**
 * 学生分类标签
 */
@code("school")
class StdLabel extends CodeBean {
  var labelType: StdLabelType = _
}

/**
 * 学生分类标签类型
 */
@code("school")
class StdLabelType extends CodeBean

/** 收费类型 */
@code("school")
class FeeType extends CodeBean {

}

/** 学生文档归档类型 */
@code("school")
class StdDocType extends CodeBean {

}

/** 毕业生类型 */
@code("school")
class GraduateType extends CodeBean {

}
