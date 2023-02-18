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

package org.openurp.edu.clazz.domain

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.ExamType
import org.openurp.edu.clazz.model.Clazz
import org.openurp.edu.exam.model.ExamTaker

trait ExamTakerProvider {
  def getStdTakers(semester: Semester, std: Student): Seq[ExamTaker]

  def getClazzTakers(clazz: Clazz, examType: ExamType): Seq[ExamTaker]

  def getCourseTakers(semester: Semester, course: Course, examType: ExamType): Seq[ExamTaker]
}

class DefaultExamTakerProvider extends ExamTakerProvider {

  var entityDao: EntityDao = _

  def getStdTakers(semester: Semester, std: Student): Seq[ExamTaker] = {
    val query = OqlBuilder.from(classOf[ExamTaker], "et")
    query.where("et.semester = :semester and et.std = :std", semester, std)
    entityDao.search(query)
  }

  def getClazzTakers(clazz: Clazz, examType: ExamType): Seq[ExamTaker] = {
    val query = OqlBuilder.from(classOf[ExamTaker], "et")
    query.where("et.semester = :semester and et.clazz = :clazz", clazz.semester, clazz)
    if (examType.id == ExamType.MakeupDelay) query.where("et.examType.id in (:examTypeIds)", Array(ExamType.Makeup, ExamType.Delay))
    else query.where("et.examType = :examType", examType)
    query.orderBy("et.std.user.code")
    entityDao.search(query)
  }

  def getCourseTakers(semester: Semester, course: Course, examType: ExamType): Seq[ExamTaker] = {
    val query = OqlBuilder.from(classOf[ExamTaker], "et")
    query.where("et.semester = :semester and et.clazz.course = :course", semester, course)
    if (examType.id == ExamType.MakeupDelay) query.where("et.examType.id in (:examTypeIds)", Array(ExamType.Makeup, ExamType.Delay))
    else query.where("et.examType = :examType", examType)
    query.orderBy("et.std.user.code")
    entityDao.search(query)
  }
}
