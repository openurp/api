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

package org.openurp.edu.program.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{DateRange, Named, Remark, Updated}
import org.openurp.base.edu.model.{Course, Major, MajorDirection}
import org.openurp.base.model.{AuditStatus, Campus, Department, EduLevelBased}
import org.openurp.base.std.model.Grade
import org.openurp.code.edu.model.*
import org.openurp.code.std.model.StdType

import java.time.Instant
import java.time.temporal.ChronoUnit
import scala.collection.mutable

/** 专业培养方案
 *
 * @author chaostone
 *
 */
class Program extends LongId, Updated, Named, Cloneable, DateRange, EduLevelBased, Remark {

  /** 年级 */
  var grade: Grade = _

  /** 部门 */
  var department: Department = _

  /** 专业 */
  var major: Major = _

  /** 专业方向 */
  var direction: Option[MajorDirection] = None

  /** 学生类别 */
  var stdTypes: mutable.Set[StdType] = Collections.newSet[StdType]

  /** 学制 */
  var duration: Float = _

  /** 学习形式 */
  var studyType: Option[StudyType] = None

  /** 要求学分 */
  var credits: Float = _

  /** 起始学期 */
  var startTerm: Short = _

  /** 结束学期 */
  var endTerm: Short = _

  /** 学期对应校区 */
  var termCampuses = Collections.newBuffer[TermCampus]

  /** 多出学分可以冲抵的课程类别 */
  var offsetType: Option[CourseType] = None

  //----学位部分--------
  /** 毕业授予学位 */
  var degree: Option[Degree] = None

  /** 学位绩点 */
  var degreeGpa: Option[Float] = None

  /** 学位课程 */
  var degreeCourses = Collections.newSet[Course]

  /** 学位审核要求的证书 */
  var degreeCertificates = Collections.newSet[Certificate]

  /** 分类标签 */
  var labels: mutable.Buffer[ProgramCourseLabel] = Collections.newBuffer[ProgramCourseLabel]

  /** 先修课程 */
  var prerequisites: mutable.Buffer[ProgramPrerequisite] = Collections.newBuffer[ProgramPrerequisite]

  /** 审核状态 */
  var status: AuditStatus = AuditStatus.Draft

  /** 审核意见 */
  var opinions: Option[String] = None

  def changeGrade(ng: Grade): Unit = {
    val yearGap = ng.beginIn.getYear - this.grade.beginIn.getYear
    this.grade = ng
    this.beginOn = this.beginOn.plus(yearGap, ChronoUnit.YEARS)
    this.endOn = this.endOn.plus(yearGap, ChronoUnit.YEARS)
  }

  def this(p: Program) = {
    this()
    this.name = p.name

    this.project = p.project
    this.grade = p.grade
    this.eduType = p.eduType
    this.level = p.level
    this.department = p.department
    this.major = p.major
    this.direction = p.direction
    this.stdTypes.addAll(p.stdTypes)
    this.studyType = p.studyType

    this.beginOn = p.beginOn
    this.endOn = p.endOn
    this.duration = p.duration
    this.startTerm = p.startTerm
    this.endTerm = p.endTerm

    this.credits = p.credits
    this.offsetType = p.offsetType

    this.degree = p.degree
    this.degreeGpa = p.degreeGpa
    this.degreeCourses.addAll(p.degreeCourses)
    this.degreeCertificates.addAll(p.degreeCertificates)

    p.termCampuses foreach { tc => this.termCampuses.addOne(new TermCampus(this, tc)) }
    p.labels foreach { l => this.labels.addOne(new ProgramCourseLabel(this, l.course, l.tag)) }
    p.prerequisites foreach { p => this.prerequisites.addOne(new ProgramPrerequisite(this, p.course, p.prerequisite)) }

    this.remark = p.remark
    this.updatedAt = Instant.now
  }

  def campuses: Set[Campus] = {
    termCampuses.map(_.campus).toSet
  }

  def terms: Short = (endTerm - startTerm + 1).asInstanceOf[Short]

  def courseTags: Map[Course, Set[ProgramCourseTag]] = {
    labels.groupBy(x => x.course).map(x => (x._1, x._2.map(_.tag).toSet))
  }

  def disciplineCode: String = {
    major.getDisciplineCode(beginOn)
  }

  def stdTypeNames: String = {
    stdTypeNames("，")
  }

  def stdTypeNames(sep: String): String = {
    stdTypes.map(_.name).mkString(sep)
  }

  /** 添加先修课程
   *
   * @param course
   * @param prerequisite
   * @return
   */
  def addPrerequisite(course: Course, prerequisite: Course): ProgramPrerequisite = {
    this.prerequisites.find(p => p.course == course && p.prerequisite == prerequisite) match {
      case None =>
        val pp = new ProgramPrerequisite(this, course, prerequisite)
        this.prerequisites.addOne(pp)
        pp
      case Some(p) => p
    }
  }

  def addLabel(course: Course, tag: ProgramCourseTag): ProgramCourseLabel = {
    this.labels.find(l => l.course == course && l.tag == tag) match {
      case None =>
        val l = new ProgramCourseLabel(this, course, tag)
        this.labels.addOne(l)
        l
      case Some(l) => l
    }
  }
}
