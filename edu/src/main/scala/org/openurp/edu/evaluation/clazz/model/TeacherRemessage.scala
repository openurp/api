/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
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
package org.openurp.edu.evaluation.clazz.model

import org.openurp.edu.base.model.Student
import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import java.time.Instant

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

  var createdAt: Instant = _

  var updatedAt: Instant = _
}
