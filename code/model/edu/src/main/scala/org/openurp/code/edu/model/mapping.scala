/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
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
package org.openurp.code.edu.model

import scala.reflect.runtime.universe

import org.beangle.data.model.annotation.code
import org.beangle.data.model.bind.Mapping
import org.openurp.code.BaseCodeBean

class DefaultMapping extends Mapping {

 def binding(): Unit = {
    bind[AdmissionType]
    bind[ClassroomUsage]
    bind[ClassroomType]
    bind[DisciplineCatalog]
    bind[Discipline]
    bind[EnrollMode]
    bind[EducationMode]
    bind[HskLevel]
    bind[Institution]
    bind[StudentPunishmentType]
    bind[StudentAlterType]
    bind[StudentAlterReason]
    bind[StudentStatus]
    bind[UeeSubjectType]
    bind[Degree]
    bind[DegreeLevel]
    bind[DisciplineCategory]
    bind[EducationDegree]
    bind[EducationLevel]
    bind[EducationResult]
    bind[Language]
    bind[LanguageAbility]
    bind[StudyType]
  }
}