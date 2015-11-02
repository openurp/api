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
package org.openurp.edu.base.code.model

import org.beangle.data.model.annotation.code
import org.openurp.code.BaseCodeBean
import org.openurp.code.edu.model.EducationLevel

/**
 * 学生分类标签
 *
 * @author chaostone
 * @since 3.0.0
 */
@code("school")
class StdLabel extends BaseCodeBean {
  var labelType: StdLabelType = _
}
/**
 * 学生分类标签类型
 *
 * @author chaostone
 * @since 3.0.0
 */
@code("school")
class StdLabelType extends BaseCodeBean

/**
 * 学生类别
 *
 * @author chaostone
 * @since 3.0.0
 */
@code("school")
class StdType extends BaseCodeBean


/**
 * 成绩记录方式
 */
@code("school")
class ScoreMarkStyle extends BaseCodeBean {

  var numStyle: Boolean = _

  def this(id: Integer) {
    this()
    this.id = id
  }
  def this(id: Integer, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = enName
  }
}

object ScoreMarkStyle {
  val Percent = Integer.valueOf(1)
}

/**
 * 考试情况
 * 正常、作弊、旷考等
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("school")
class ExamStatus extends BaseCodeBean {
  /**
   * 是否参加考试
   */
  var attended: Boolean = _
  /**
   * 是否需要参加下一次缓考
   */
  var deferred: Boolean = _
  /**
   * 能否录入成绩
   */
  var inputable: Boolean = _

  def this(id: Integer) {
    this()
    this.id = id
  }
}
object ExamStatus {

  /** 正常 */
  val Normal = 1;

  /** 缓考 */
  val Delay = 2;

  /** 旷缺 */
  val Absent = 3;

  /** 违纪 */
  val Violation = 4;

  /** 作弊 */
  val Cheat = 5;

  /**无考试资格*/
  val Unqualify = 6;

  /**其他*/
  val Misc = 9;
}

/**
 * 课程类别
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("school")
class CourseType extends BaseCodeBean {

  /**是否实践课程*/
  var practical: Boolean = _

  def this(id: Integer, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = enName
  }
}

/**
 * 课程种类
 * （一般、体育、挂牌、双语）
 *
 * @author chaostone
 * @since 2005-11-17
 */
@code("school")
class CourseCategory extends BaseCodeBean
/**
 * 考核方式
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("school")
class ExamMode extends BaseCodeBean

/**
 * 课时类别代码
 */
@code("school")
class CourseHourType extends BaseCodeBean

/**
 * 课程能力等级
 */
@code("school")
class CourseAbilityRate extends BaseCodeBean

/**
 * 教材类型
 */
@code("school")
class BookType extends BaseCodeBean

/**
 * 教材获奖类型
 */
@code("school")
class BookAwardType extends BaseCodeBean

/**
 * 培养层次
 */
@code("school")
class Education extends BaseCodeBean {
  var from: EducationLevel = _
  var to: EducationLevel = _
}

