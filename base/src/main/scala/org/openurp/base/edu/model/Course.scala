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
import org.beangle.data.model.pojo.*
import org.openurp.base.model.{Department, ProjectBased, Semester}
import org.openurp.base.std.model.Grade
import org.openurp.code.edu.model.*

import java.time.LocalDate

/**
 * 课程基本信息 </p>
 * 记录课程代码、名称、学分、课时等基本信息，课程的关键业务属性为课程名称、学分、课时、考核方式等与课程有关的属性，其它类似课程类别、所属部门等
 * 均可以看作非关键属性。 </p> 如课程不要求记录学分、不做考核要求、不计算绩点等额外要求需要培养方案、成绩等环节进行额外处理，不在课程部分进行规定。
 * <p>
 * 课程的学历层次可以不加指定，为空时表示适用与对应项目下的所有学历层次。
 *
 * @author chaostone
 * @since 2008-09-24
 */
class Course extends LongId, ProjectBased, Ordered[Course], Updated, TemporalOn, Coded, Named, EnNamed, Remark {
  /** 培养层次要求 */
  var levels = Collections.newBuffer[CourseLevel]
  /** 院系 */
  var department: Department = _
  /** 默认学分 */
  var defaultCredits: Float = _

  /** 课程模块 */
  var module: Option[CourseModule] = None
  /** 必修/选修/限选 */
  var rank: Option[CourseRank] = None
  /** 课程类别 */
  var courseType: Option[CourseType] = None
  /** 课程性质 (理论、实践、术科、实验) */
  var nature: CourseNature = _

  /** 学时/总课时 */
  var creditHours: Int = _
  /** 分类课时 */
  var hours = Collections.newBuffer[CourseHour]
  /** 实践周 */
  var weeks: Option[Int] = None
  /** 周课时 */
  var weekHours: Int = _
  /** 考试方式 */
  var examMode: ExamMode = _
  /** 成绩记录方式 */
  var gradingMode: GradingMode = _
  /** 是否计算绩点 * */
  var calgp: Boolean = _
  /** 是否有补考 */
  var hasMakeup: Boolean = _

  /** 课程分类 */
  var categories = Collections.newSet[CourseCategory]
  /** 课程分组 */
  var cluster: Option[CourseCluster] = None
  /** 课程建设过程 */
  var journals = Collections.newBuffer[CourseJournal]
  /** 课程标签 */
  var tags = Collections.newSet[CourseTag]

  override def compare(other: Course): Int = {
    code.compareTo(other.code)
  }

  /** 查找给定日期的名称
   *
   * @param date
   * @return
   */
  def getNameOn(date: LocalDate): String = {
    journals.find(_.within(date)).map(_.name).getOrElse(name)
  }

  /** 查找给定日期的英文名称
   *
   * @param date
   * @return
   */
  def getEnNameOn(date: LocalDate): String = {
    journals.find(_.within(date)).flatMap(_.enName).getOrElse(enName.getOrElse(name))
  }

  def this(id: Long, code: String, name: String, enName: String) = {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Option(enName)
  }

  /** 是否实践课程 */
  def practical: Boolean = {
    nature.practical
  }

  def getCredits(level: EducationLevel): Float = {
    levels.find(_.level == level) match {
      case None => defaultCredits
      case Some(cl) => cl.credits.getOrElse(defaultCredits)
    }
  }

  def getJournal(grade: Grade): CourseJournal = {
    journals.find(_.contains(grade)) match
      case None => new CourseJournal(this, grade.beginIn.atDay(1))
      case Some(j) => j
  }

  def getJournal(semester: Semester): CourseJournal = {
    journals.find(_.within(semester.beginOn)) match
      case None => new CourseJournal(this, semester.beginOn)
      case Some(j) => j
  }

  def getHour(grade: Grade, nature: TeachingNature): Option[Int] = {
    journals.find(_.contains(grade)) match
      case None => hours.find(_.nature == nature).map(_.creditHours)
      case Some(j) => j.getHour(nature)
  }

  def getWeek(grade: Grade, nature: TeachingNature): Option[Int] = {
    journals.find(_.contains(grade)) match
      case None => this.weeks
      case Some(j) => j.weeks
  }

  def creditsInfo: String = {
    if levels.isEmpty then defaultCredits.toString
    else {
      val sb = Collections.newBuffer[String]
      var containDefault = false
      levels foreach { l =>
        l.credits foreach { c =>
          sb.append(l.level.name + " " + c.toString)
          if (java.lang.Float.compare(this.defaultCredits, c) == 0) containDefault = true
        }
      }
      if !containDefault then sb.prepend(defaultCredits.toString)
      sb.mkString(" ")
    }
  }

  def addHour(nature: TeachingNature, hours: Int): Unit = {
    this.hours.find(_.nature == nature) match {
      case None =>
        val nh = new CourseHour
        nh.nature = nature
        nh.creditHours = hours
        nh.course = this
        this.hours.addOne(nh)
      case Some(h) => h.creditHours += hours
    }
  }

  def updateHours(newHours: Map[TeachingNature, Int]): Unit = {
    val cshours = this.hours.map(x => (x.nature, x)).toMap
    newHours foreach { h =>
      cshours.get(h._1) match {
        case None => this.addHour(h._1, h._2)
        case Some(h) => h.creditHours = h.creditHours
      }
    }
    val newNatures = newHours.keys.toSet
    val abandoned = this.hours.find(x => newNatures.contains(x.nature))
    this.hours.subtractAll(abandoned)
  }

  def updateHour(nature: TeachingNature, hours: Int): Unit = {
    this.hours.find(_.nature == nature) match {
      case None =>
        if (hours > 0) {
          val nh = new CourseHour(this, nature, hours)
          this.hours.addOne(nh)
        }
      case Some(h) =>
        if (hours > 0) {
          h.creditHours = hours
        } else {
          this.hours.subtractOne(h)
        }
    }
  }

  def description: String = {
    s"$code $name"
  }

  /** 学时是否一致
   *
   * @return
   */
  def creditHourIdentical: Boolean = {
    hours.map(_.creditHours).sum == creditHours
  }
}

/**
 * 课程分类课时信息
 *
 * @author chaostone
 */
class CourseHour extends LongId {
  var course: Course = _
  var creditHours: Int = _
  var nature: TeachingNature = _

  def this(course: Course, nature: TeachingNature, hours: Int) = {
    this()
    this.course = course
    this.nature = nature
    this.creditHours = hours
  }
}

/**
 * 课程层次要求
 */
class CourseLevel extends LongId {
  var course: Course = _
  var level: EducationLevel = _
  var credits: Option[Float] = _

  def this(c: Course, l: EducationLevel) = {
    this()
    this.course = c
    this.level = l
  }
}
