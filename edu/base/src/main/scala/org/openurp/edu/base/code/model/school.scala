/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.code.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean
import org.openurp.code.edu.model.EducationLevel

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
 * 课程种类
 * （一般、体育、挂牌、双语）
 */
@code("school")
class CourseCategory extends CodeBean
/**
 * 课时类别代码
 */
@code("school")
class CourseHourType extends CodeBean

/**
 * 课程能力等级
 */
@code("school")
class CourseAbilityRate extends CodeBean
/**
 * 修课类别
 * （重修、增修、免修不免试、主修，选修）
 *
 * @author chaostone
 * @since 2005-12-2
 */
@code("school")
class CourseTakeType extends CodeBean {
  def this(id: Int, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Some(enName)
  }
}
object CourseTakeType {

  /** 正常修读  */
  val Normal = 1;

  /** 重修 */
  val Restudy = 3;

  /** 免修 */
  val Untake = 5;

}
/**
 * 培养层次
 */
@code("school")
class Education extends CodeBean {
  var from: EducationLevel = _
  var to: EducationLevel = _
}
/**
 * 考核方式
 */
@code("school")
class ExamMode extends CodeBean
/**
 * 考试情况
 * 正常、作弊、旷考等
 */
@code("school")
class ExamStatus extends CodeBean {
  /**
   * 是否参加考试
   */
  var attended: Boolean = _
  /**
   * 是否需要参加下一次缓考
   */
  var deferred: Boolean = _
  /**
   * 是否有作弊行为
   */
  var cheating: Boolean = _

  def this(id: Int) {
    this()
    this.id = id
  }
}

object ExamStatus {

  /** 正常 */
  val Normal = 1;

  /**缺考*/
  val Absent = 3;
}

@code("school")
class ElectionMode extends CodeBean

object ElectionMode {
  /** 指定 */
  val Assigned = 1

  /** 自选  */
  val SelfChoose = 2
}

/**
 * 考试方式
 */
@code("school")
class ExamForm extends CodeBean
/**
 * 考试类型
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
class ExamType extends CodeBean {

  /**是否是缓考*/
  var deferred: Boolean = _

  def this(id: Int, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Some(enName)
  }
}
/**
 * 成绩类型
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("school")
class GradeType extends CodeBean {
  def isGa: Boolean = {
    id == GradeType.EndGa || id == GradeType.MakeupGa || id == GradeType.DelayGa
  }
  def this(id: Int) {
    this()
    this.id = id
  }

  def this(id: Int, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Some(enName)
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
 * 成绩记录方式
 */
@code("school")
class ScoreMarkStyle extends CodeBean {

  var numStyle: Boolean = _

  def this(id: Int) {
    this()
    this.id = id
  }
  def this(id: Int, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Some(enName)
  }
}

object ScoreMarkStyle {
  val Percent = 1
}
/**
 * 授课语言类型
 */
@code("school")
class TeachLangType extends CodeBean
/**
 * 教师类型
 */
@code("school")
class TeacherType extends CodeBean {
  var external: Boolean = _
  var parttime: Boolean = _
}
