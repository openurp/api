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
import org.beangle.commons.lang.Strings
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Named
import org.openurp.code.edu.model.{SyllabusTopicLabel, TeachingMethod, TeachingNature}

/** 教学大纲-教学主题
 */
class SyllabusTopic extends LongId, Named {

  /** 教学大纲 */
  var syllabus: Syllabus = _

  /** 大纲内顺序 */
  var idx: Short = _

  /** 教学内容 */
  var contents: String = _

  /** 自主学习学时 */
  var learningHours: Int = _

  /** 其他要素 */
  var elements = Collections.newBuffer[SyllabusTopicElement]

  /** 教学方式 */
  var methods = Collections.newBuffer[TeachingMethod]

  /** 对应课程目标 */
  var objectives: Option[String] = None

  /** 分类课时 */
  var hours = Collections.newBuffer[SyllabusTopicHour]

  def getHour(nature: TeachingNature): Option[SyllabusTopicHour] = {
    hours.find(_.nature == nature)
  }

  def getElement(label: SyllabusTopicLabel): Option[SyllabusTopicElement] = {
    elements.find(_.label == label)
  }

  def matchedObjectives: Seq[SyllabusObjective] = {
    objectives match
      case None => List.empty
      case Some(s) => Strings.split(s).flatMap(x => syllabus.objectives.find(_.code == x)).toSeq
  }
}
