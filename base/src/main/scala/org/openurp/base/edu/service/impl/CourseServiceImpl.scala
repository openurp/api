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

package org.openurp.base.edu.service.impl

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.base.edu.model.{Course, CourseJournal}
import org.openurp.base.edu.service.CourseService

class CourseServiceImpl extends CourseService {

  var entityDao: EntityDao = _

  override def rebuild(course: Course): Unit = {
    entityDao.refresh(course)
    //计算journals的结束日期
    val jq = OqlBuilder.from(classOf[CourseJournal], "j")
    jq.where("j.course=:course", course)
    jq.orderBy("j.beginOn")
    val origin =entityDao.search(jq)
    origin.foreach{ o=>
      origin.find( i=> i.id!=o.id )
    }
    val journals = TemporalOn.calcEndOn(origin)

    entityDao.saveOrUpdate(journals)

    //last one
    val last = journals.last
    if (last.endOn.isEmpty) {
      //复制基本信息
      if last.enName.nonEmpty then course.enName = last.enName
      course.name = last.name
      //复制课时和周数
      course.creditHours = last.creditHours
      course.weekHours = last.weekHours
      course.weeks = last.weeks
      course.examMode = last.examMode
      course.department = last.department
      course.updateHours(last.hours.map(x => (x.nature, x.creditHours)).toMap)
      entityDao.saveOrUpdate(course)
    }
    if (journals.nonEmpty) {
      course.beginOn = journals.map(_.beginOn).min
      if (journals.exists(_.endOn.isEmpty)) {
        course.endOn = None
      } else {
        course.endOn = Some(journals.map(_.endOn.get).max)
      }
    }
  }

}
