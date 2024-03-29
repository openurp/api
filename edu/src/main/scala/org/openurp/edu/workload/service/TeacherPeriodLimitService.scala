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

package org.openurp.edu.workload.service

import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.Semester
import org.openurp.code.hr.model.StaffType
import org.openurp.code.job.model.ProfessionalGrade

trait TeacherPeriodLimitService {

  def getMaxPeriod(teacher: Teacher): Int

  def getMaxPeriod(grade: ProfessionalGrade, staffType: StaffType): Int

  def getTeacherPeriods(teacher: Teacher, semester: Semester): Int
}
