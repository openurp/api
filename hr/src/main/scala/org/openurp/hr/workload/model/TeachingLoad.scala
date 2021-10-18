/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.hr.workload.model

import org.beangle.data.model.LongId
import org.openurp.base.edu.model.{Course, Semester, Teacher}
import org.openurp.code.job.model.ProfessionalTitle

class TeachingLoad extends LongId {

  var crn: String = _

  var course: Course = _

  var teacher: Teacher = _

  var semester: Semester = _

  var teacherTitle: ProfessionalTitle = _

  var capacityFactor: CapacityFactor = _

  var clazzTags: String = _

  var factor: Float = _

  var creditHours: Int = _

  var stdCount: Int = _

  var loadHours: Float = _

}
