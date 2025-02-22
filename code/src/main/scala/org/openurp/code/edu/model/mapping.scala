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

package org.openurp.code.edu.model

import org.beangle.data.orm.{IdGenerator, MappingModule}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator(classOf[Int], IdGenerator.Code)
    bind[AdmissionType]
    bind[ActivityType]
    bind[CourseNature]
    bind[DisciplineCatalog]
    bind[Discipline]
    bind[DisciplineCategory]
    bind[Level1Discipline]
    bind[EnrollMode]
    bind[EducationMode]
    bind[HskLevel]
    bind[Institution]
    bind[InstitutionCategory]
    bind[Degree]
    bind[DegreeLevel]
    bind[EducationDegree]
    bind[AcademicLevel]
    bind[EducationResult]
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

    bind[TeachingForm]
    bind[TeachingMethod]
    bind[TeachingSection]

    bind[CertificateCategory]
    bind[Certificate] declare { e =>
      e.subjects is length(500)
    }
    bind[ClazzTag]
    bind[BookType]
    bind[BookAwardType]
    bind[CourseType]
    bind[CourseCategory].declare { e =>
      e.children is depends("parent")
    }
    bind[CourseCategoryDimension]
    bind[CourseAbilityRate]
    bind[CourseAbilitySubject]
    bind[EducationType]
    bind[ExamDeferReason]
    bind[CourseModule]
    bind[CourseRank]
    bind[SyllabusTopicLabel]
    bind[GraduateObjective]

    bind[CourseAwardType]
    bind[CourseAwardCategory]
    bind[ExperimentType]
    bind[ExperimentCategory]
    bind[ThesisTopicSource]
    bind[ThesisType]
    bind[CourseTag]
    bind[ProgramCourseTag]
    bind[ClazzArchiveDoc]
    all.cacheAll()
  }
}
