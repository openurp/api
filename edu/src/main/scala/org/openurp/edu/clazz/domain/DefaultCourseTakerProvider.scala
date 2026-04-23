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
import org.openurp.base.service.SemesterService
import org.openurp.base.std.model.Student
import org.openurp.edu.clazz.model.CourseTaker
import org.openurp.edu.his.model.HisCourseTaker

class DefaultCourseTakerProvider extends CourseTakerProvider {
  var entityDao: EntityDao = _

  var semesterService: SemesterService = _

  override def get(std: Student): Seq[CourseTaker] = {
    val terms = semesterService.get(std.project, std.beginOn, std.endOn)
    if (terms._2.isEmpty) {
      getHisTakers(std, terms._1.map(_.schoolYear.substring(0, 4).toInt).toSet)
    } else if (terms._1.isEmpty) {
      getCurTakers(std)
    } else {
      getHisTakers(std, terms._1.map(_.schoolYear.substring(0, 4).toInt).toSet) ++ getCurTakers(std)
    }
  }

  private def getHisTakers(std: Student, schoolYears: Iterable[Int]): Seq[CourseTaker] = {
    val query = OqlBuilder.from(classOf[HisCourseTaker], "ct")
    query.where("ct.std = :std", std)
    query.where("ct.schoolYear in (:schoolYears)", schoolYears)
    entityDao.search(query).map(_.convert()).sortBy(_.semester.beginOn)
  }

  private def getCurTakers(std: Student): Seq[CourseTaker] = {
    val query = OqlBuilder.from(classOf[CourseTaker], "ct")
    query.where("ct.std = :std", std)
    entityDao.search(query).sortBy(_.semester.beginOn)
  }
}
