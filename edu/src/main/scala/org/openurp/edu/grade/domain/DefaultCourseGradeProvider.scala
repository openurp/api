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

import org.beangle.commons.collection.Collections
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.model.Semester
import org.openurp.base.service.SemesterService
import org.openurp.base.std.model.Student
import org.openurp.edu.grade.model.{CourseGrade, Grade}
import org.openurp.edu.his.model.HisCourseGrade

class DefaultCourseGradeProvider extends CourseGradeProvider {

  var entityDao: EntityDao = _

  var semesterService: SemesterService = _

  override def getPublished(std: Student, semesters: Iterable[Semester]): Seq[CourseGrade] = {
    get(std, Some(Grade.Status.Published), semesters)
  }

  override def getAll(std: Student, semesters: Iterable[Semester]): Seq[CourseGrade] = {
    get(std, None, semesters)
  }

  override def getPassedStatus(std: Student): collection.Map[Long, Boolean] = {
    val terms = semesterService.get(std.project, std.beginOn, std.endOn)
    val rs = Collections.newBuffer[Array[Any]]
    if (terms._2.isEmpty) {
      rs.addAll(getHisPassedStatus(std, terms._1.map(_.schoolYear.substring(0, 4).toInt).toSet))
    } else if (terms._1.isEmpty) {
      rs.addAll(getCurPassedStatus(std))
    } else {
      rs.addAll(getHisPassedStatus(std, terms._1.map(_.schoolYear.substring(0, 4).toInt).toSet))
      rs.addAll(getCurPassedStatus(std))
    }
    val courseMap = Collections.newMap[Long, Boolean]
    for (obj <- rs) {
      val courseId = obj(0).asInstanceOf[Number].longValue
      if (null != obj(1)) {
        if (!courseMap.contains(courseId) || !courseMap(courseId)) {
          courseMap.put(courseId, obj(1).asInstanceOf[java.lang.Boolean].booleanValue)
        }
      } else {
        courseMap.put(courseId, false)
      }
    }
    courseMap
  }

  private def get(std: Student, status: Option[Int], semesters: Iterable[Semester]): Seq[CourseGrade] = {
    val terms =
      if semesters.isEmpty then semesterService.get(std.project, std.beginOn, std.endOn)
      else semesters.partition(_.year.archived)

    if (terms._2.isEmpty) {
      getHisGrades(std, status, semesters, terms._1.map(_.schoolYear.substring(0, 4).toInt).toSet)
    } else if (terms._1.isEmpty) {
      getCurGrades(std, status, semesters)
    } else {
      getHisGrades(std, status, semesters, terms._1.map(_.schoolYear.substring(0, 4).toInt).toSet) ++ getCurGrades(std, status, semesters)
    }
  }

  private def getCurPassedStatus(std: Student): Seq[Array[Any]] = {
    val query = OqlBuilder.from(classOf[CourseGrade], "grade")
    query.where("grade.std = :std", std)
    query.where("grade.project = :project", std.project)
    query.where("grade.status = :status", Grade.Status.Published)
    query.select("grade.course.id,grade.passed")
    entityDao.search(query).asInstanceOf[Seq[Array[Any]]]
  }

  private def getHisPassedStatus(std: Student, schoolYears: Iterable[Int]): Seq[Array[Any]] = {
    val query = OqlBuilder.from(classOf[HisCourseGrade], "grade")
    query.where("grade.std = :std", std)
    query.where("grade.project = :project", std.project)
    query.where("grade.status = :status", Grade.Status.Published)
    query.where("grade.schoolYear in (:schoolYears)", schoolYears)
    query.select("grade.course.id,grade.passed")
    entityDao.search(query).asInstanceOf[Seq[Array[Any]]]
  }

  private def getCurGrades(std: Student, status: Option[Int], semesters: Iterable[Semester]): Seq[CourseGrade] = {
    val query = OqlBuilder.from(classOf[CourseGrade], "grade")
    query.where("grade.std = :std", std)
    query.where("grade.project = :project", std.project)
    status foreach (s => query.where("grade.status =:status", s))
    if semesters.nonEmpty then query.where("grade.semester in(:semesters)", semesters)
    entityDao.search(query).sortBy(_.semester.beginOn)
  }

  private def getHisGrades(std: Student, status: Option[Int], semesters: Iterable[Semester], schoolYears: Iterable[Int]): Seq[CourseGrade] = {
    val query = OqlBuilder.from(classOf[HisCourseGrade], "grade")
    query.where("grade.std = :std", std)
    query.where("grade.project = :project", std.project)
    query.where("grade.schoolYear in (:schoolYears)", schoolYears)
    status foreach (s => query.where("grade.status =:status", s))
    if semesters.nonEmpty then query.where("grade.semester in(:semesters)", semesters)
    entityDao.search(query).map(_.convert()).sortBy(_.semester.beginOn)
  }

}
