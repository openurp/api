/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.code.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean
import org.openurp.code.edu.model.AcademicLevel

/**
 * 教材类型
 */
@code("school")
class BookType extends CodeBean

/**
 * 教材获奖类型
 */
@code("school")
class BookAwardType extends CodeBean

/**
 * 课程类别
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("school")
class CourseType extends CodeBean {

  /**是否实践课程*/
  var practical: Boolean = _

  def this(id: Int, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Some(enName)
  }
}
/**
 * 课时类别代码
 */
@code("school")
class CourseHourType extends CodeBean

/**
 * 课程能力等级
 */
@code("school")
class CourseAbilityRate extends CodeBean {
  var rate: Int = _
}
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

/**
 * 学生类别
 */
@code("school")
class StdType extends CodeBean

/**
 * 教师类型
 */
@code("school")
class TeacherType extends CodeBean {
  var external: Boolean = _
  var parttime: Boolean = _
}
