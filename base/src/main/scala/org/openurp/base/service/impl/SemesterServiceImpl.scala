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

package org.openurp.base.service.impl

import org.beangle.commons.collection.Order
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.model.{Project, Semester}
import org.openurp.base.service.SemesterService

import java.time.LocalDate

class SemesterServiceImpl extends SemesterService {
  var entityDao: EntityDao = _

  override def get(project: Project, date: LocalDate): Semester = {
    val builder = OqlBuilder.from(classOf[Semester], "semester")
      .where("semester.calendar =:calendar", project.calendar)
    builder.where(":date between semester.beginOn and  semester.endOn", date)
    builder.cacheable()
    val rs = entityDao.search(builder)
    if (rs.isEmpty) {
      val builder2 = OqlBuilder.from(classOf[Semester], "semester")
        .where("semester.calendar =:calendar", project.calendar)
      builder2.where("semester.endOn >= :date", date)
      builder2.orderBy(Order.parse("semester.beginOn"))
      builder2.cacheable()
      builder.limit(1, 1)
      entityDao.search(builder2).headOption match {
        case Some(r) => r
        case None => getActives(project).last
      }
    } else {
      rs.head
    }
  }

  override def getActives(project: Project): Seq[Semester] = {
    val builder = OqlBuilder.from(classOf[Semester], "semester")
      .where("semester.calendar =:calendar", project.calendar)
    builder.where("semester.archived=false")
    builder.orderBy("semester.beginOn")
    builder.cacheable()
    entityDao.search(builder)
  }

  /**
   * get semester by index
   *
   * @param project
   * @param beginOn
   * @param endOn
   * @param index start with 1
   */
  override def get(project: Project, beginOn: LocalDate, endOn: LocalDate, index: Int): Option[Semester] = {
    val builder = OqlBuilder.from(classOf[Semester], "semester")
      .where("semester.calendar =:calendar", project.calendar)
    builder.where("semester.beginOn <= :endOn", endOn)
    builder.where("semester.endOn >= :beginOn", beginOn)
    builder.orderBy(Order.parse("semester.beginOn"))
    val semestersByInterval = entityDao.search(builder)
    if (index > semestersByInterval.size) {
      None
    } else {
      Some(semestersByInterval(index - 1))
    }
  }
}
