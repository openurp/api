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

package org.openurp.edu.grade.domain

import org.openurp.code.edu.model.{CourseTakeType, GradeType}
import org.openurp.edu.grade.model.CourseGrade

object GradeFilters {

  def chain(filters: GradeFilter*): Chain = {
    new Chain(filters)
  }

  class Chain(filters: Seq[GradeFilter]) extends GradeFilter {
    override def filter(grades: Iterable[CourseGrade]): Iterable[CourseGrade] = {
      var rs = grades
      filters foreach { filter =>
        rs = filter.filter(rs)
      }
      rs
    }
  }

  /** 只要及格的 */
  object Passed extends GradeFilter {
    override def filter(grades: Iterable[CourseGrade]): Iterable[CourseGrade] = {
      grades.filter(_.passed)
    }
  }

  /** 只要不免修的 */
  object NotExemption extends GradeFilter {
    override def filter(grades: Iterable[CourseGrade]): Iterable[CourseGrade] = {
      grades.filter(_.courseTakeType.id != CourseTakeType.Exemption)
    }
  }

  /** 缓考没出成绩的不要 */
  object Stable extends GradeFilter {
    override def filter(grades: Iterable[CourseGrade]): Iterable[CourseGrade] = {
      val deplayGradeType = new GradeType(GradeType.Delay)
      val endGradeType = new GradeType(GradeType.End)
      grades.filter { grade =>
        if !grade.passed && grade.getGrade(deplayGradeType).isEmpty then
          !grade.getExamGrade(endGradeType).exists(_.examStatus.hasDeferred)
        else true
      }
    }
  }

}
