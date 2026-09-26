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

package org.openurp.edu.workload.model

import org.beangle.data.model.LongId
import org.openurp.base.edu.model.Course
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.Semester
import org.openurp.code.job.model.ProfessionalTitle
import org.openurp.edu.workload.config.CapacityFactor
import scala.compiletime.uninitialized

/** 教学工作量
 */
class TeachingLoad extends LongId {

  var crn: String = uninitialized

  var course: Course = uninitialized

  var teacher: Teacher = uninitialized

  var semester: Semester = uninitialized

  var teacherTitle: ProfessionalTitle = uninitialized

  var capacityFactor: CapacityFactor = uninitialized

  var clazzTags: String = uninitialized

  var factor: Float = uninitialized

  var creditHours: Int = uninitialized

  var stdCount: Int = uninitialized

  var loadHours: Float = uninitialized

}
