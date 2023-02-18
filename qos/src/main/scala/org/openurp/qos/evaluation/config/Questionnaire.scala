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

package org.openurp.qos.evaluation.config

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.{Remark, TemporalOn, Updated}
import org.openurp.base.model.{Department, Project}

/** 评教问卷
 */
@config
class Questionnaire extends LongId with Updated with TemporalOn with Remark {
  var project: Project = _
  /** 问卷标题 */
  var title: String = _
  /** 简单描述 */
  var description: String = _
  /** 相关联的问题 */
  var questions = Collections.newBuffer[Question]

  def totalScore: Float = {
    questions.foldLeft(0f)(_ + _.score)
  }
}
