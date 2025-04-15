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
import org.beangle.commons.lang.Strings
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{DateRange, Updated}
import org.openurp.base.edu.model.*
import org.openurp.base.model.*
import org.openurp.code.edu.model.*

import java.time.Instant
import java.util.Locale

/** 课程教学大纲
 *
 */
class Syllabus extends LongId, Updated, DateRange {

  /** 课程 */
  var course: Course = _

  /** 语种 */
  var docLocale: Locale = _

  /** 简介 */
  var description: String = _

  /** 生效学年学期 */
  var semester: Semester = _

  //object targets
  /** 面向的培养层次 */
  var levels = Collections.newSet[EducationLevel]

  /** 面向的专业 */
  var majors = Collections.newSet[Major]

  /** 总学时 */
  var creditHours: Int = _

  /** 总实践周 */
  var weeks: Option[Int] = None

  /** 分类课时 */
  var hours = Collections.newBuffer[SyllabusHour]

  /** 教学方式 */
  var methods: String = _

  /** 考核课时 */
  var examCreditHours: Int = _

  /** 自主学习课时 */
  var learningHours: Float = _

  //course natures
  /** 学期中的开课阶段 */
  var stage: Option[CalendarStage] = None

  /** 课程模块 */
  var module: CourseModule = _

  /** 必修选修 */
  var rank: CourseRank = _

  /** 课程性质 */
  var nature: CourseNature = _

  /** 考试方式 */
  var examMode: ExamMode = _

  /** 计分方式 */
  var gradingMode: GradingMode = _

  // other course relations
  /** 先修课程 */
  var prerequisites: Option[String] = None

  /** 并修课程 */
  var corequisites: Option[String] = None

  /** 后续课程 */
  var subsequents: Option[String] = None

  //obe
  /** 课程目标 */
  var objectives = Collections.newBuffer[SyllabusObjective]

  /** 对毕业要求的支撑 */
  var outcomes = Collections.newBuffer[SyllabusOutcome]

  //topic and lessons
  /** 教学内容 */
  var topics = Collections.newBuffer[SyllabusTopic]

  /** 考核办法 */
  var assessments = Collections.newBuffer[SyllabusAssessment]

  //textbooks and resources
  /** 教材 */
  var textbooks = Collections.newBuffer[Textbook]

  /** 参考书目 */
  var bibliography: Option[String] = None

  /** 其他教学资源 */
  var materials: Option[String] = None

  /** 课程网站地址 */
  var website: Option[String] = None

  //text sections
  var texts = Collections.newBuffer[SyllabusText]

  /** 教学设计 */
  var designs = Collections.newBuffer[SyllabusMethodDesign]

  /** 教学案例 */
  var cases = Collections.newBuffer[SyllabusCase]

  /** 教学实验 */
  var experiments = Collections.newBuffer[SyllabusExperiment]

  //admin and audit infoes
  /** 开课院系 */
  var department: Department = _

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

  /** 大纲自动检查是否完整 */
  var complete: Boolean = _

  def teachingMethods: Seq[String] = {
    methods match
      case null => Seq.empty
      case m => Strings.split(m, Array('、', ',', '；', '，')).toSeq
  }

  def teachingNatures: Seq[TeachingNature] = hours.map(_.nature).toSeq

  def getCreditHour(nature: TeachingNature): Option[SyllabusHour] = {
    hours.find(_.nature == nature)
  }

  def getTopicCreditHours(nature: TeachingNature): Float = {
    topics.flatMap(t => t.getHour(nature).map(_.creditHours)).sum
  }

  def getCreditHours(nature: TeachingNature): Float = {
    topics.flatMap(t => t.getHour(nature).map(_.creditHours)).sum
  }

  def getText(name: String): Option[SyllabusText] = {
    texts.find(_.name == name)
  }

  def getObjective(code: String): Option[SyllabusObjective] = {
    objectives.find(_.code == code)
  }

  def getOutcome(title: String): Option[SyllabusOutcome] = {
    outcomes.find(_.title == title)
  }

  def getAssessments(gradeType: GradeType): Seq[SyllabusAssessment] = {
    assessments.filter(x => x.gradeType == gradeType).toSeq
  }

  def getAssessment(gradeType: GradeType, componentName: String): Option[SyllabusAssessment] = {
    if (null == componentName) {
      assessments.find(x => x.gradeType == gradeType && x.component.isEmpty)
    } else {
      assessments.find(x => x.gradeType == gradeType && x.component.contains(componentName))
    }
  }

  def getUsualAssessment(index: Int): Option[SyllabusAssessment] = {
    assessments.find(x => x.gradeType.id == GradeType.Usual && x.component.nonEmpty && x.idx == index)
  }
}

object Syllabus {

  def copy(syllabus: Syllabus, semester: Semester, course: Course): Syllabus = {
    val newer = new Syllabus
    newer.semester = semester
    newer.beginOn = semester.beginOn
    newer.endOn = semester.endOn
    newer.docLocale = syllabus.docLocale
    newer.status = AuditStatus.Draft

    //copy course info
    newer.course = course
    val journal =
      course.journals.find(_.within(semester.beginOn)) match
        case None => new CourseJournal(course, semester.beginOn)
        case Some(j) => j
    newer.department = journal.department
    newer.creditHours = journal.creditHours
    newer.weeks = syllabus.weeks
    syllabus.hours foreach { h =>
      val nh = new SyllabusHour(newer, h.nature, h.creditHours)
      newer.hours.addOne(nh)
    }
    newer.learningHours = syllabus.learningHours
    newer.examCreditHours = syllabus.examCreditHours

    //copy syllabus basis info
    newer.rank = syllabus.rank
    newer.module = syllabus.module
    newer.stage = syllabus.stage
    newer.nature = syllabus.nature
    newer.examMode = syllabus.examMode
    newer.gradingMode = syllabus.gradingMode
    newer.methods = syllabus.methods
    newer.description = syllabus.description
    newer.subsequents = syllabus.subsequents
    newer.prerequisites = syllabus.prerequisites
    newer.levels.addAll(syllabus.levels)
    newer.majors.addAll(syllabus.majors)

    //copy objectives
    syllabus.objectives foreach { obj =>
      val nobj = new SyllabusObjective(newer, obj.code, obj.name, obj.contents)
      newer.objectives.addOne(nobj)
    }
    //copy outcomes
    syllabus.outcomes foreach { oc =>
      val noc = new SyllabusOutcome(newer, oc.idx, oc.title, oc.contents, oc.courseObjectives)
      newer.outcomes.addOne(noc)
    }
    //copy topics
    syllabus.topics foreach { topic =>
      val nt = new SyllabusTopic
      nt.idx = topic.idx
      nt.exam = topic.exam
      nt.name = topic.name
      nt.contents = topic.contents
      nt.learningHours = topic.learningHours
      nt.methods = topic.methods
      nt.objectives = topic.objectives
      topic.elements foreach { ele =>
        val ne = new SyllabusTopicElement(nt, ele.label, ele.contents)
        nt.elements.addOne(ne)
      }
      topic.hours foreach { h =>
        val nh = new SyllabusTopicHour(nt, h.nature, h.creditHours)
        nt.hours.addOne(nh)
      }
      nt.syllabus = newer
      newer.topics.addOne(nt)
    }

    // copy designs
    syllabus.designs foreach { d =>
      val nd = new SyllabusMethodDesign(newer, d.idx, d.name, d.contents, d.hasCase, d.hasExperiment)
      newer.designs.addOne(nd)
    }
    syllabus.cases foreach { c =>
      val nc = new SyllabusCase(newer, c.idx, c.name)
      newer.cases.addOne(nc)
    }
    syllabus.experiments foreach { e =>
      val ne = new SyllabusExperiment(newer, e.idx, e.name, e.creditHours, e.experimentType, e.online)
      newer.experiments.addOne(ne)
    }
    //copy assessment
    syllabus.assessments foreach { a =>
      val na = new SyllabusAssessment(newer, a.gradeType, a.component)
      na.idx = a.idx
      na.description = a.description
      na.scorePercent = a.scorePercent
      na.scoreTable = a.scoreTable
      na.assessCount = a.assessCount
      na.objectivePercents = a.objectivePercents
      newer.assessments.addOne(na)
    }
    syllabus.texts foreach { t =>
      val nt = new SyllabusText(newer, t.indexno, t.name, t.contents)
      newer.texts.addOne(nt)
    }
    //copy resources
    newer.materials = syllabus.materials
    newer.bibliography = syllabus.bibliography
    newer.website = syllabus.website
    newer.textbooks.addAll(syllabus.textbooks)
    newer.updatedAt = Instant.now
    newer
  }
}
