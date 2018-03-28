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
package org.openurp.edu.lesson.model

import org.beangle.data.model.Component
import org.openurp.edu.base.code.model.{ ExamForm, ExamMode }
import org.beangle.commons.lang.time.HourMinute

/**
 * 考试安排
 */
class ExamSchedule extends Component with Serializable with Cloneable {

  var examOn: Option[java.sql.Date] = None

  var beginAt: HourMinute = HourMinute.Zero

  var endAt: HourMinute = HourMinute.Zero

  /** 考试方式 */
  var examMode: ExamMode = _

}
