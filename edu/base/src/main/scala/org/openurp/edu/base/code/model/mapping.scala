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
package org.openurp.edu.base.code.model

import scala.reflect.runtime.universe
import org.beangle.data.model.annotation.code
import org.beangle.data.orm.MappingModule
import org.openurp.code.CodeBean
import org.beangle.commons.lang.time.WeekState

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[BookType]
    bind[BookAwardType]
    bind[CourseType]
    bind[CourseCategory]
    bind[CourseTakeType]
    bind[CourseHourType]
    bind[CourseAbilityRate]
    bind[DayPart]
    bind[EduSpan]
    bind[ElectionMode]
    bind[ExamType]
    bind[ExamStatus]
    bind[ExamMode]
    bind[ExamForm]
    bind[GradeType]
    bind[StdLabel]
    bind[StdLabelType]
    bind[StdType]
    bind[GradingMode]
    bind[TeachLangType]
    bind[TeacherType]
  }
}
