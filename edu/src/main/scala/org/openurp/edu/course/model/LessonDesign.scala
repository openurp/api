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

package org.openurp.edu.course.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.commons.lang.math.SmallInterval
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark

import java.time.LocalDate

/**
 * 具体授课内容和方法设计
 */
@beta
class LessonDesign extends LongId, Remark {

  def this(program: ClazzProgram, idx: Int) = {
    this()
    this.program = program
    this.idx = idx
  }

  /** 教案 */
  var program: ClazzProgram = _

  /** 序号(从1开始) */
  var idx: Int = _

  /** 上课日期 */
  var lessonOn: LocalDate = _

  /** 开始和结束小节 */
  var units: SmallInterval = _

  /** 学时 */
  var creditHours: Int = _

  /** 教学主题 */
  var subject: String = _

  /** 课程文字说明 */
  var texts = Collections.newBuffer[LessonDesignText]

  /** 课程内容环节 */
  var sections = Collections.newBuffer[LessonDesignSection]

  /** 课后作业明细 */
  var homework: Option[String] = None

  /** 附件 */
  var filePath: Option[String] = None

  def getSection(idx: Int): Option[LessonDesignSection] = {
    sections.find(_.idx == idx)
  }

  def getText(name: String): Option[LessonDesignText] = {
    texts.find(_.name == name)
  }

  def get(name: String): Option[String] = {
    getText(name).map(_.contents)
  }

}
