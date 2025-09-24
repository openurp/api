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

import org.openurp.base.edu.model.Course
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.{CourseTakeType, EducationLevel}
import org.openurp.edu.grade.model.CourseGrade
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import java.text.DecimalFormat
import scala.math.BigDecimal.RoundingMode

class WeightedMeanTest extends AnyFunSpec, Matchers {
  val std = new Student
  std.level = new EducationLevel

  val c1 = new Course()
  c1.defaultCredits = 0.5f

  val c2 = new Course()
  c2.defaultCredits = 2.0f

  val c3 = new Course()
  c3.defaultCredits = 3.0f

  val normal = new CourseTakeType(CourseTakeType.Normal,"1","正常",null)

  val g1 = new CourseGrade
  g1.courseTakeType = normal
  g1.course = c1
  g1.score = Some(82f)
  g1.gp = Some(3.3f)
  g1.std = std

  val g2 = new CourseGrade
  g2.courseTakeType = normal
  g2.course = c2
  g2.score = Some(88f)
  g2.gp = Some(3.7f)
  g2.std = std

  val g3 = new CourseGrade
  g3.courseTakeType = normal
  g3.course = c3
  g3.score = Some(100f)
  g3.gp = Some(4.0f)
  g3.std = std

  val grades = List(g1, g2, g3)

  describe("WeightedMean") {
    it("calc ga") {
      val format = DecimalFormat("##0.##")
      val ga = MeanScoreMethod.WeightedMean.calcScore(grades).setScale(2, RoundingMode.HALF_UP)
      assert(format.format(ga) == "94")
    }
    it("calc gpa") {
      assert(MeanScoreMethod.WeightedMean.calcGpa(grades).setScale(2, RoundingMode.HALF_UP).toString() == "3.83")
    }
  }

}
