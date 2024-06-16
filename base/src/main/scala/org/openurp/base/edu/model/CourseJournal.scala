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

package org.openurp.base.edu.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{EnNamed, Named, TemporalOn, Updated}
import org.openurp.base.model.Department
import org.openurp.base.std.model.Grade
import org.openurp.code.edu.model.{ExamMode, TeachingNature}

import java.time.LocalDate

/** 课程变化日志
 */
class CourseJournal extends LongId, Named, EnNamed, Updated, TemporalOn {

  /** 课程 */
  var course: Course = _

  /** 开课部门 */
  var department: Department = _

  /** 考核方式 */
  var examMode: ExamMode = _

  /** 学时/总课时 */
  var creditHours: Int = _

  /** 周数 */
  var weeks: Int = _

  /** 周课时 */
  var weekHours: Int = _

  /** 分类课时 */
  var hours = Collections.newBuffer[CourseJournalHour]

  def this(course: Course, beginOn: LocalDate) = {
    this()
    this.course = course
    this.department = course.department
    this.examMode = course.examMode
    this.creditHours = course.creditHours
    this.weeks = course.weeks
    course.hours foreach { h =>
      this.hours.addOne(new CourseJournalHour(this, h.nature, h.creditHours, h.weeks))
    }
    this.beginOn = beginOn
  }

  def getHour(nature: TeachingNature): Option[Int] = {
    hours.find(_.nature == nature).map(_.creditHours)
  }

  def getWeek(nature: TeachingNature): Option[Int] = {
    hours.find(_.nature == nature).map(_.weeks)
  }

  def contains(grade: Grade): Boolean = this.within(grade.beginOn)
}

class CourseJournalHour extends LongId {
  def this(journal: CourseJournal, nature: TeachingNature, creditHours: Int, weeks: Int) = {
    this()
    this.journal = journal
    this.nature = nature
    this.creditHours = creditHours
    this.weeks = weeks
  }

  var journal: CourseJournal = _
  var creditHours: Int = _
  var weeks: Int = _
  var nature: TeachingNature = _
}
