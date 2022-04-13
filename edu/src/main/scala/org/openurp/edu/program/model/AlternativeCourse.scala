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

package org.openurp.edu.program.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.model.Department
import org.openurp.code.edu.model.AcademicLevel
import org.openurp.base.edu.code.model.StdType
import org.openurp.base.edu.model._

/**
 * 课程替代关系.
 * @author new
 */
trait AlternativeCourse extends LongId with Updated {

  /** 原课程 */
  var olds = Collections.newSet[Course]

  /** 新课程 */
  var news = Collections.newSet[Course]

  def exchange(): Unit = {
    val nolds = Collections.newSet[Course]
    nolds ++= news
    news.clear()
    news ++= olds
    olds.clear()
    olds ++= nolds
  }
}

/**
 * 专业替代课程.
 */
class MajorAlternativeCourse extends AlternativeCourse with Remark {

  /**
   * 项目
   */
  var project: Project = _

  /**
   * 获取培养层次
   */
  var level: AcademicLevel = _
  /**
   * 起始年级.
   */
  var fromGrade: String = _

  /** 截至年级 */
  var toGrade: String = _

  /**
   * 院系
   */
  var department: Option[Department] = None

  /**
   * 适用专业.
   */
  var major: Option[Major] = None

  /**
   * 适用方向.
   */
  var direction: Option[Direction] = None

  /**
   * 学生类别
   */
  var stdType: Option[StdType] = None
}

/**
 * 学生替代课程.
 */
class StdAlternativeCourse extends AlternativeCourse with Remark {

  /**
   * 获取学生
   */
  var std: Student = _
}
