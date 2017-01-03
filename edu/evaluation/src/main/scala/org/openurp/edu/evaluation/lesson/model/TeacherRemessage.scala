/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2016, OpenURP Software.
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
package org.openurp.edu.evaluation.lesson.model

import org.openurp.edu.base.model.Student
import org.beangle.commons.collection.Collections
import org.beangle.commons.model.LongId
import java.util.Date
/**
 * 文字评教教师回复
 *
 * @author chaostone
 */
class TeacherRemessage extends LongId {
  /** 回复信息 */
  var remessage: String = _

  /** 回复对象 */
  var students = Collections.newSet[Student]

  /** 文字评教 */
  var textEvaluation: TextEvaluation = _

  /** 显示状态 */
  var visible: Boolean = false

  var createdAt: Date = _

  var updatedAt: Date = _
}
