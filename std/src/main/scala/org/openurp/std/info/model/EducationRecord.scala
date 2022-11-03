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

package org.openurp.std.info.model

import org.beangle.data.model.LongId
import org.openurp.base.std.model.Student

import java.time.YearMonth

/**
 * 教育经历
 */
@deprecated(message = "Using EduWorkRecord instead", since = "0.30.4")
class EducationRecord extends LongId {

  /** 学生 */
  var std: Student = _

  var beginOn: YearMonth = _

  var endOn: YearMonth = _

  var school: String = _

}
