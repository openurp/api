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

package org.openurp.edu.course.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.TeachingOffice
import org.openurp.base.model.{AuditStatus, Semester, User}
import org.openurp.edu.clazz.model.Clazz

import java.time.Instant
import scala.collection.mutable

/** 授课计划(教学进度表）
 * 每个任务唯一
 */
class ClazzPlan extends LongId, Updated {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 学期 */
  var semester: Semester = _

  /** 分环节课时 */
  var hours = Collections.newBuffer[ClazzSectionHour]

  /** 课堂学时 */
  var lessonHours: Int = _

  /** 课外学时 */
  var extraHours: Int=_

  /** 考核课时 */
  var examHours: Int = _

  /** 授课内容 */
  var lessons: mutable.Buffer[Lesson] = Collections.newBuffer[Lesson]

  /** 附件文件路径 */
  var filePath: Option[String] = None

  /** 教研室 */
  var office: Option[TeachingOffice] = None

  /** 状态 */
  var status: AuditStatus = AuditStatus.Draft

  /** 作者 */
  var writer: User = _

  /** 审核人 */
  var reviewer: Option[User] = None

  /** 院长 */
  var approver: Option[User] = None

  /** 发布时间 */
  var publishAt: Option[Instant] = None

  /** 驳回意见 */
  var opinions: Option[String] = None

  def this(clazz: Clazz) = {
    this()
    this.clazz = clazz
    this.semester = clazz.semester
    this.updatedAt = Instant.now
  }

  def getHours(section: String): Int = {
    hours.filter(_.name == section).map(_.creditHours).headOption.getOrElse(0)
  }

  def getLesson(idx: Int): Option[Lesson] = {
    lessons.find(_.idx == idx)
  }

  def reserveHours(names: Iterable[String]): Unit = {
    val nameSet = names.toSet
    val removed = hours.filter(x => !nameSet.contains(x.name))
    hours.subtractAll(removed)
  }

  def addHour(section: String, creditHours: Int): Unit = {
    hours.find(_.name == section) match
      case None =>
        val s = new ClazzSectionHour(this, section, creditHours)
        hours.addOne(s)
      case Some(s) =>
        s.creditHours = creditHours
  }

  def copyTo(p: ClazzPlan): Unit = {
    val sectionNames = this.hours.map(_.name).toSet
    val abandons = p.hours.filter(x => !sectionNames.contains(x.name))
    p.hours.subtractAll(abandons)

    this.hours foreach { h =>
      p.hours.find(x => x.name == h.name) match
        case None => p.hours.addOne(new ClazzSectionHour(p, h.name, h.creditHours))
        case Some(nh) => nh.creditHours = h.creditHours
    }

    p.writer = this.writer
    p.office = this.office
    p.reviewer = this.reviewer
    p.lessonHours = this.lessonHours
    p.examHours = this.examHours

    val targetLessons = p.lessons.sortBy(_.idx)
    var i = 0
    this.lessons.sortBy(_.idx) foreach { l =>
      val nl = if i < targetLessons.length then targetLessons(i) else new Lesson(p, i + 1)
      nl.contents = l.contents
      nl.homework = l.homework
      nl.learningHours = l.learningHours
      nl.learning = l.learning
      nl.forms = l.forms
      if (!nl.persisted) p.lessons.addOne(nl)
      i += 1
    }
    p.updatedAt = Instant.now
  }
}
