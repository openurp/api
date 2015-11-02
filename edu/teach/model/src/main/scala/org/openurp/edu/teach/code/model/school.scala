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
package org.openurp.edu.teach.code.model

import org.beangle.data.model.annotation.code
import org.openurp.code.BaseCodeBean
import org.openurp.edu.teach.code._

@code("school")
class ElectionMode extends BaseCodeBean

object ElectionMode {
  /**
   * 指定
   */
  val ASSIGEND: Integer = 1

  /**
   * 自选
   */
  val SELF: Integer = 2
}
@code("school")
class ExamForm extends BaseCodeBean
@code("school")
class TeachLangType extends BaseCodeBean

/**
 * 考试类型
 *
 * @author 塞外狂人
 * @author chaostone
 * @since 2005-9-7
 */
object ExamType {

  /** 期末考试 */
  val Final = 1

  /** 期中考试 */
  val Midterm = 2

  /** 补考 */
  val Makeup = 3

  /** 缓考 */
  val Delay = 4

}
@code("school")
class ExamType extends BaseCodeBean {
  var gradeType: GradeType = _
  def delay: Boolean = {
    id == 4
  }
  def this(id: Integer, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = enName
  }
}

/**
 * 修课类别
 * （重修、增修、免修不免试、主修，选修）
 *
 * @author chaostone
 * @since 2005-12-2
 */
@code("school")
class CourseTakeType extends BaseCodeBean {
  /** 是否重修 */
  var retake: Boolean = _
  /** 是否考核 */
  var exam: Boolean = _
  /**是否上课*/
  var attend: Boolean = _
  def this(id: Integer, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = enName
  }
}
object CourseTakeType {

  /**
   * 正常修读
   */
  val NORMAL = 1;

  /**
   * 重修
   */
  val RESTUDY = 3;

  /**
   * 免修不免考
   */
  val REEXAM = 4;

  /**
   * 免修
   */
  val UNTAKE = 5;

}

/**
 * 成绩类型
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("school")
class GradeType extends BaseCodeBean {
  def isGa: Boolean = {
    id == GradeType.EndGa || id == GradeType.MakeupGa || id == GradeType.DelayGa
  }
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
object GradeType {
  val Final = 0

  val Middle = 1
  val End = 2
  val Usual = 3
  val Makeup = 4
  val Delay = 6

  val EndGa = 7
  val DelayGa = 8
  val MakeupGa = 9
}
/**
 * 教学任务标签
 */
@code("school")
class LessonTag extends BaseCodeBean
object LessonTag {
  val GuapaiId = 1

  val ElectableId = 2
}

