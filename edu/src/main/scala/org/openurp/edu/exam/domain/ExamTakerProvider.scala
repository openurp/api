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

package org.openurp.edu.exam.domain

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.Semester
import org.openurp.base.service.SemesterService
import org.openurp.base.std.model.Student
import org.openurp.code.edu.model.ExamType
import org.openurp.edu.clazz.model.Clazz
import org.openurp.edu.exam.model.ExamTaker
import org.openurp.edu.his.model.HisExamTaker

trait ExamTakerProvider {
  def get(std: Student): Seq[ExamTaker]

  def get(std: Student, semester: Semester): Seq[ExamTaker]

  def getTakers(clazz: Clazz, examType: ExamType): Seq[ExamTaker]

  def getTakers(semester: Semester, course: Course, examType: ExamType): Seq[ExamTaker]

}

class DefaultExamTakerProvider extends ExamTakerProvider {
  var entityDao: EntityDao = _
  var semesterService: SemesterService = _

  override def get(std: Student): Seq[ExamTaker] = {
    val terms = semesterService.get(std.project, std.beginOn, std.endOn)
    if (terms._2.isEmpty) {
      getHisTakers(std, terms._1.map(_.year.startYear).toSet)
    } else if (terms._1.isEmpty) {
      getCurTakers(std)
    } else {
      getHisTakers(std, terms._1.map(_.year.startYear).toSet) ++ getCurTakers(std)
    }
  }

  override def get(std: Student, semester: Semester): Seq[ExamTaker] = {
    if (semester.archived) {
      val query = OqlBuilder.from(classOf[HisExamTaker], "ct")
      query.where("ct.std = :std and ct.semester=:semester", std, semester)
      query.where("ct.schoolYear=:schoolYear", semester.year.startYear)
      entityDao.search(query).map(_.convert())
    } else {
      val query = OqlBuilder.from(classOf[ExamTaker], "ct")
      query.where("ct.std = :std and ct.semester=:semester", std, semester)
      entityDao.search(query)
    }
  }

  override def getTakers(clazz: Clazz, examType: ExamType): Seq[ExamTaker] = {
    val query = OqlBuilder.from(classOf[ExamTaker], "et")
    query.where("et.semester = :semester and et.clazz = :clazz", clazz.semester, clazz)
    if (examType.id == ExamType.MakeupDelay) query.where("et.examType.id in (:examTypeIds)", Array(ExamType.Makeup, ExamType.Delay))
    else query.where("et.examType = :examType", examType)
    query.orderBy("et.std.user.code")
    entityDao.search(query)
  }

  override def getTakers(semester: Semester, course: Course, examType: ExamType): Seq[ExamTaker] = {
    val query = OqlBuilder.from(classOf[ExamTaker], "et")
    query.where("et.semester = :semester and et.clazz.course = :course", semester, course)
    if (examType.id == ExamType.MakeupDelay) query.where("et.examType.id in (:examTypeIds)", Array(ExamType.Makeup, ExamType.Delay))
    else query.where("et.examType = :examType", examType)
    query.orderBy("et.std.user.code")
    entityDao.search(query)
  }

  private def getHisTakers(std: Student, schoolYears: Iterable[Int]): Seq[ExamTaker] = {
    val query = OqlBuilder.from(classOf[HisExamTaker], "ct")
    query.where("ct.std = :std", std)
    query.where("ct.schoolYear in (:schoolYears)", schoolYears)
    entityDao.search(query).map(_.convert()).sortBy(_.semester.beginOn)
  }

  private def getCurTakers(std: Student): Seq[ExamTaker] = {
    val query = OqlBuilder.from(classOf[ExamTaker], "ct")
    query.where("ct.std = :std", std)
    entityDao.search(query).sortBy(_.semester.beginOn)
  }
}
