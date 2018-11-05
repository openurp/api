/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
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
package org.openurp.edu.exam.model

import org.beangle.data.model.LongId
import org.openurp.edu.course.model.Clazz
import org.openurp.code.edu.model.ExamType

/**
 * 排考教学任务
 */
class ExamClazz extends LongId {

  /**教学班*/
  var clazz: Clazz = _

  /**考试类型*/
  var examType: ExamType = _

  /**排考任务*/
  var task: Option[ExamTask] = _

  /**考生数*/
  var stdCount: Int = _

  /**考试周*/
  var examWeek: Option[Int] = None

  /**是否院系自主安排*/
  var departArranged: Boolean = _

  /**试卷编号*/
  var examPaperNo: Option[String] = None

}
