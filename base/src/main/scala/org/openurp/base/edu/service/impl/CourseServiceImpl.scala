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
    val journals = entityDao.search(jq)
    if (journals.size > 1) {
      var i = 0
      while (i < journals.length - 1) { //最后一个不处理
        val j = journals(i)
        val jNext = journals(i + 1)
        j.endOn = Some(jNext.beginOn.minusMonths(1))
        i += 1
      }
      entityDao.saveOrUpdate(journals)
    }
    //last one
    val last = journals.last
    if (last.endOn.isEmpty) {
      //复制基本信息
      if (last.enName.nonEmpty) {
        course.enName = last.enName
      }
      course.name = last.name

      //复制课时和周数
      course.creditHours = last.creditHours
      course.weekHours = last.weekHours
      course.weeks = last.weeks
      val newHours = last.hours.map(x => (x.nature, x.creditHours)).toMap
      course.updateHours(newHours)

      course.examMode = last.examMode
      course.department = last.department
      entityDao.saveOrUpdate(course)
    }
  }

}
