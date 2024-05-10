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

package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{Semester, User}
import org.openurp.code.edu.model.TeachingSection

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

  /** 自主学习课时 */
  var learningHours: Int = _

  /** 考核课时 */
  var examHours: Int = _

  /** 作者 */
  var author: Option[User] = None

  /** 分环节课时 */
  var hours = Collections.newBuffer[TeachingPlanHour]

  /** 授课内容 */
  var lessons: mutable.Buffer[Lesson] = Collections.newBuffer[Lesson]

  /** 文件路径 */
  var filePath: Option[String] = None

  /** 是否通过 */
  var passed: Option[Boolean] = None

  /** 审核人 */
  var auditor: Option[User] = None

  /** 审核时间 */
  var auditAt: Option[Instant] = None

  def getHours(section: TeachingSection): Int = {
    hours.filter(_.section == section).map(_.creditHours).headOption.getOrElse(0)
  }

  def getLesson(idx: Int): Option[Lesson] = {
    lessons.find(_.idx == idx)
  }

  def this(clazz: Clazz) = {
    this()
    this.clazz = clazz
    this.semester = clazz.semester
  }

  def copyTo(p: TeachingPlan): Unit = {
    p.docLocale = this.docLocale
    p.learningHours = this.learningHours
    p.examHours = this.examHours
    p.hours.clear()
    this.hours foreach { h =>
      val nh = new TeachingPlanHour
      nh.plan = p
      nh.section = h.section
      nh.creditHours = h.creditHours
      p.hours.addOne(nh)
    }
    p.lessons.clear()
    p.author = this.author
    this.lessons foreach { l =>
      val nl = new Lesson
      nl.idx = l.idx
      nl.contents = l.contents
      nl.homework = l.homework
      nl.learningHours = l.learningHours
      nl.learning = l.learning
      nl.methods.addAll(l.methods)
      nl.plan = p
      p.lessons.addOne(nl)
    }
    p.updatedAt = Instant.now
  }
}
