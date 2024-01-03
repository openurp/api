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

package org.openurp.edu.exam.config

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.openurp.base.model.Project
import org.openurp.code.edu.model.ExamType

@config
class ExamDeferSetting extends LongId {

  var project: Project = _

  /** 考试类型 */
  var examType: ExamType = _

  /** 申请提前量(天) */
  var daysBeforeApply: Int = _

  /** 是否开放申请 */
  var applyOpened: Boolean = _
}
