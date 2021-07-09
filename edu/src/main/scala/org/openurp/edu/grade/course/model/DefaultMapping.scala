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
package org.openurp.edu.grade.course.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    //grade
    bind[CourseGrade].declare { e =>
      e.crn is length(20)
      e.course & e.courseTakeType & e.project & e.courseType & e.semester & e.gradingMode are notnull
      e.operator is length(100)
      e.scoreText is length(5)
      e.remark is length(200)
      e.examGrades & e.gaGrades are depends("courseGrade")

      index("", true, e.std, e.course, e.semester, e.crn)
      index("", false, e.std)
      index("", false, e.clazz)
      index("", false, e.project)
    }

    bind[ExamGrade].declare { e =>
      index("", true, e.courseGrade, e.gradeType)
    }

    bind[GaGrade].declare { e =>
      index("", true, e.courseGrade, e.gradeType)
    }

    bind[CourseGradeState].declare { e =>
      e.examStates is depends("gradeState")
      e.gaStates is depends("gradeState")
      index("", false, e.clazz)
    }

    bind[ExamGradeState].declare { e =>
      index("", true, e.gradeState, e.gradeType)
    }

    bind[GaGradeState].declare { e =>
      e.remark is length(50)
      index("", true, e.gradeState, e.gradeType)
    }

    bind[StdGpa].declare { e =>
      e.semesterGpas is depends("stdGpa")
      e.yearGpas is depends("stdGpa")
      index("", true, e.std)
    }

    bind[StdSemesterGpa].declare { e =>
      index("", true, e.stdGpa, e.semester)
    }

    bind[StdYearGpa].declare { e =>
      index("", true, e.stdGpa, e.schoolYear)
    }
  }

}
