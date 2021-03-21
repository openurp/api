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
package org.openurp.code.edu.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[AdmissionType]
    bind[ActivityType]
    bind[ClassroomType]
    bind[CourseNature]
    bind[DisciplineCatalog]
    bind[Discipline]
    bind[EnrollMode]
    bind[EducationMode]
    bind[HskLevel]
    bind[Institution]
    bind[Degree]
    bind[DegreeLevel]
    bind[DisciplineCategory]
    bind[EducationDegree]
    bind[AcademicLevel]
    bind[EducationResult]
    bind[Language]
    bind[LanguageAbility]
    bind[StudyType]
    bind[EducationLevel]
    bind[EduCategory]

    bind[CourseTakeType]
    bind[DayPart]
    bind[ElectionMode]
    bind[ExamType]
    bind[ExamStatus]
    bind[ExamMode]
    bind[ExamForm]
    bind[GradeType]
    bind[GradingMode]
    bind[TeachLangType]
    bind[TeachingNature]
    bind[TeachingMethod]
    all.cacheAll()
  }
}
