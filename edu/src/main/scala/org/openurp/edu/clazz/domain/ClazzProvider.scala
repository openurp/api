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
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Project, Semester}
import org.openurp.base.service.SemesterService
import org.openurp.base.std.model.{Squad, Student}
import org.openurp.edu.clazz.model.{Clazz, ClazzRestrictionMeta, CourseTaker}
import org.openurp.edu.his.model.HisCourseTaker

trait ClazzProvider {
  def getClazzes(semester: Semester, std: Student): Seq[Clazz]

  def getClazzes(semester: Semester, squad: Squad): Seq[Clazz]

  def getClazzes(semester: Semester, teacher: Teacher, project: Project): Seq[Clazz]

  def getClazzes(semester: Semester, teacher: Teacher): Seq[Clazz]
}

class DefaultClazzProvider extends ClazzProvider {
  var entityDao: EntityDao = _
  var semesterService: SemesterService = _

  override def getClazzes(semester: Semester, squad: Squad): Seq[Clazz] = {
    val builder = OqlBuilder.from(classOf[Clazz], "clazz")
    builder.where("clazz.project = :project", squad.project)
    builder.where("clazz.semester = :semester", semester)
    val con = RestrictionHelper.build(ClazzRestrictionMeta.Squad, "lgi", squad.id.toString)
    val params = con.params
    builder.where("exists(from clazz.enrollment.restrictions lg join lg.items as lgi where" + con.content + ")", params(0))
    entityDao.search(builder)
  }

  override def getClazzes(semester: Semester, std: Student): Seq[Clazz] = {
    if (semester.year.archived) {
      val query = OqlBuilder.from(classOf[HisCourseTaker], "ct")
      query.where("ct.clazz.project=:project and ct.semester=:semester and ct.std = :std", std.project, semester, std)
      entityDao.search(query).map(_.clazz)
    } else {
      val query = OqlBuilder.from(classOf[CourseTaker], "ct")
      query.where("ct.clazz.project=:project and ct.semester=:semester and ct.std = :std", std.project, semester, std)
      entityDao.search(query).map(_.clazz)
    }
  }

  override def getClazzes(semester: Semester, teacher: Teacher, project: Project): Seq[Clazz] = {
    val query = OqlBuilder.from(classOf[Clazz], "clazz")
    query.where("clazz.project=:project and clazz.semester=:semester", project, semester)
    query.where(":teacher in elements(clazz.teachers)", teacher)
    entityDao.search(query)
  }

  override def getClazzes(semester: Semester, teacher: Teacher): Seq[Clazz] = {
    val query = OqlBuilder.from(classOf[Clazz], "clazz")
    query.where("clazz.semester=:semester", semester)
    query.where(":teacher in elements(clazz.teachers)", teacher)
    entityDao.search(query)
  }
}
