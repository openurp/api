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
package org.openurp.edu.grade.model

import org.beangle.data.orm.MappingModule
import org.openurp.code.edu.model.GradeType
import org.openurp.edu.grade.course.model._
import org.openurp.edu.grade.moral.model.MoralGrade

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    //code
    bind[GradeType]

    //grade
    bind[CourseGrade].declare { e =>
      e.crn is length(20)
      e.course & e.courseTakeType & e.project & e.courseType & e.semester & e.gradingMode are notnull
      e.operator is length(100)
      e.scoreText is length(5)
      e.remark is length(200)
      e.examGrades & e.gaGrades are depends("courseGrade")

      index("idx_course_grade", true, e.std, e.course, e.semester)
      index("idx_course_grade_std", false, e.std)
      index("idx_course_grade_clazz", false, e.clazz)
      index("idx_course_grade_project", false, e.project)
    }

    bind[Grade].declare { e =>
      e.gradingMode is notnull
      e.scoreText is length(5)
      e.operator is length(100)
    }

    bind[ExamGrade].declare { e =>
      index("idx_exam_grade", true, e.courseGrade, e.gradeType)
    }

    bind[GaGrade].declare { e =>
      index("idx_exam_grade", true, e.courseGrade, e.gradeType)
    }

    bind[AbstractGradeState].declare { e =>
      e.gradingMode & e.beginOn are notnull
      e.operator is length(100)
    }

    bind[CourseGradeState].declare { e =>
      e.examStates is depends("courseGradeState")
      e.gaStates is depends("courseGradeState")
      index("idx_course_grade_state", false, e.clazz)
    }

    bind[ExamGradeState].declare { e =>
      index("idx_exam_grade_state", true, e.gradeState, e.gradeType)
    }

    bind[GaGradeState].declare { e =>
      e.remark is length(50)
      index("idx_ga_grade_state", true, e.gradeState, e.gradeType)
    }

    bind[StdGpa].declare { e =>
      e.semesterGpas is depends("stdGpa")
      e.yearGpas is depends("stdGpa")
      index("idx_std_gpa", true, e.std)
    }

    bind[StdSemesterGpa].declare { e =>
      index("idx_std_semester_gpa", true, e.stdGpa, e.semester)
    }

    bind[StdYearGpa].declare { e =>
      index("idx_std_year_gpa", true, e.stdGpa, e.schoolYear)
    }

    bind[GradeRateConfig].declare { e =>
      e.gradingMode is notnull
      e.items is depends("config")
    }

    bind[GradeRateItem].declare { e =>
      e.config is notnull
    }

    bind[MoralGrade].declare { e =>
      e.scoreText is length(5)
      e.operator is length(100)
      index("idx_moral_grade", true, e.std, e.semester)
    }
  }

}
