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

package org.openurp.base.edu.code

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean

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
 * 从专业培养方案角度进行划分
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("school")
class CourseType extends CodeBean {

  /** 是否实践课程 */
  var practical: Boolean = _

  /** 是否专业课 */
  var major: Boolean = _

  /** 是否选修 */
  var optional: Boolean = _

  /** 上级类别 */
  var parent: Option[CourseType] = None

  def this(id: Int, code: String, name: String, enName: String) = {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Some(enName)
  }
}

/** 课程分类
 * 从课程内容进行划分，一般分为英语课、体育课等
 */
@code("school")
class CourseCategory extends CodeBean

/**
 * 课程能力等级
 */
@code("school")
class CourseAbilityRate extends CodeBean {
  var rate: Int = _
}

/** 培养类型 */
@code("school")
class EducationType extends CodeBean {

}
