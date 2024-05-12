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
import org.openurp.base.model.{AuditStatus, Semester, User}
import org.openurp.edu.clazz.model.Clazz
import org.openurp.edu.course.model.Lesson

import java.time.Instant
import java.util.Locale
import scala.collection.mutable

/** 授课计划
 * 每个任务唯一
 */
class TeachingPlan extends LongId with Updated {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 授课计划语言 */
  var docLocale: Locale = _

  /** 学期 */
  var semester: Semester = _

  /** 分环节课时 */
  var sections = Collections.newBuffer[TeachingPlanSection]

  /** 授课内容 */
  var lessons: mutable.Buffer[Lesson] = Collections.newBuffer[Lesson]

  /** 文件路径 */
  var filePath: Option[String] = None

  /** 状态 */
  var status: AuditStatus = AuditStatus.Draft

  /** 作者 */
  var writer: Option[User] = None

  /** 审核人 */
  var auditor: Option[User] = None

  /** 院长 */
  var dean: Option[User] = None

  /** 发布时间 */
  var publishAt: Option[Instant] = None

  def getHours(section: String): Int = {
    sections.filter(_.name == section).map(_.creditHours).headOption.getOrElse(0)
  }

  def getLesson(idx: Int): Option[Lesson] = {
    lessons.find(_.idx == idx)
  }

  def this(clazz: Clazz) = {
    this()
    this.clazz = clazz
    this.semester = clazz.semester
  }

  def reserveSections(names: Iterable[String]): Unit = {
    val nameSet = names.toSet
    val removed = sections.filter(x => !nameSet.contains(x.name))
    sections.subtractAll(removed)
  }

  def addSection(name: String, creditHours: Int): Unit = {
    sections.find(_.name == name) match
      case None =>
        val s = new TeachingPlanSection(this, name, creditHours)
        sections.addOne(s)
      case Some(s) =>
        s.creditHours = creditHours
  }

  def copyTo(p: TeachingPlan): Unit = {
    p.docLocale = this.docLocale
    p.sections.clear()
    this.sections foreach { h =>
      val nh = new TeachingPlanSection
      nh.plan = p
      nh.name = h.name
      nh.creditHours = h.creditHours
      p.sections.addOne(nh)
    }
    p.lessons.clear()
    p.writer = this.writer
    this.lessons foreach { l =>
      val nl = new Lesson
      nl.idx = l.idx
      nl.contents = l.contents
      nl.homework = l.homework
      nl.learningHours = l.learningHours
      nl.learning = l.learning
      nl.forms = l.forms
      nl.plan = p
      p.lessons.addOne(nl)
    }
    p.updatedAt = Instant.now
  }
}
