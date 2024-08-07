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

package org.openurp.code.edu.model

import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.Hierarchical
import org.openurp.code.CodeBean

/** 校外考试证书 */
@code("school")
class Certificate extends CodeBean {
  var category: CertificateCategory = _
  var institutionCode: Option[String] = None
  var institutionName: Option[String] = None
  /** 证书内课程 */
  var subjects: Option[String] = None
}

/**
 * 校外考试证书类型
 */
@code("school")
class CertificateCategory extends CodeBean

/**
 * 教学任务标签
 */
@code("school")
class ClazzTag extends CodeBean {
  var color: String = _
}

object ClazzTag {
  val GuapaiId = 1

  val ElectableId = 2
}

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

object CourseModule {
  val General = 1 //通识课程或者公共课程
  val Major = 2 //专业课程
  val Practical = 3 //实践课程
}

/** 课程模块
 */
@code("school")
class CourseModule extends CodeBean {
  var major: Boolean = _
  var practical: Boolean = _

  /** 是否是通识课程或者公共课
   *
   * @return
   */
  def general: Boolean = !major && !practical
}

/** 课程类别
 * 从专业培养方案角度进行划分
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("school")
class CourseType extends CodeBean {

  /** 简称 */
  var shortName: Option[String] = None

  /** 课程模块 */
  var module: Option[CourseModule] = None

  /** 课程属性 */
  var rank: Option[CourseRank] = None

  /** 是否实践课程 */
  @deprecated
  var practical: Boolean = _

  /** 是否专业课 */
  @deprecated
  var major: Boolean = _

  /** 是否选修 */
  @deprecated
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

/** 分类维度 */
@code("school")
class CourseCategoryDimension extends CodeBean {

}

/** 课程分类
 * 从课程内容进行划分，一般分为英语课、体育课等
 */
@code("school")
class CourseCategory extends CodeBean, Hierarchical[CourseCategory] {
  var dimension: CourseCategoryDimension = _
  var color: Option[String] = None
}

/**
 * 课程能力等级
 */
@code("school")
class CourseAbilityRate extends CodeBean {
  var rate: Int = _
  var subject: CourseAbilitySubject = _
}

/**
 * 课程能力等级分类
 */
@code("school")
class CourseAbilitySubject extends CodeBean

/** 培养类型 */
@code("school")
class EducationType extends CodeBean {

}

/** 考试缓考原因
 */
@code("school")
class ExamDeferReason extends CodeBean

/** 授课形式
 * 线下授课、线上直播、线上录播、线上线下同步等
 */
@code("school")
class TeachingForm extends CodeBean

object TeachingForm {
  val Offline = 1 //线下授课
}

/** 教学方式方法
 * 讲授，案例分析，项目训练
 */
@code("school")
class TeachingMethod extends CodeBean

/** 教学环节
 * 讲授，上机课、期中考试，习题课、讨论课
 */
@code("school")
class TeachingSection extends CodeBean

/** 教学主题其他元素的类型
 * 教学目的与要求，复习与作业要求，考核要点，辅助教学活动等
 */
@code("school")
class SyllabusTopicLabel extends CodeBean

/** 毕业目标和要求
 */
@code("school")
class GraduateObjective extends CodeBean

/** 课程获奖类型
 */
@code("school")
class CourseAwardType extends CodeBean {
  var category: CourseAwardCategory = _
}

/** 课程获奖分类
 */
class CourseAwardCategory extends CodeBean

/** 课程标签
 */
@code("school")
class CourseTag extends CodeBean {
  /** 简称 */
  var shortName: Option[String] = None

  /** 符号 */
  var sign: Option[String] = None
}

/** 培养方案课程标签
 */
@code("school")
class ProgramCourseTag extends CodeBean {
  /** 简称 */
  var shortName: Option[String] = None

  /** 符号 */
  var sign: Option[String] = None
}
