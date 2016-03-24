/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.course.domain

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.openurp.edu.base.ProjectBased
import org.openurp.edu.base.code.model.{ ExamStatus, ScoreMarkStyle }
import org.openurp.edu.base.model.Project
import org.openurp.edu.grade.code.model.GradeType
import org.openurp.edu.lesson.code.model.ExamType
/**
 * 课程成绩配置
 *
 * @author chaostone
 */
class CourseGradeSetting extends LongId with ProjectBased {

  var precision: Short = 0
  /**
   * 总评成绩的组成部分
   */
  var endGaElements = Collections.newSet[GradeType]

  /**
   * 总评成绩的组成部分
   */
  var delayGaElements = Collections.newSet[GradeType]
  /**
   * 总评成绩的组成部分
   */
  var makeupGaElements = Collections.newSet[GradeType]

  /**
   * 允许补考考试类型
   */
  var allowExamStatuses = Collections.newSet[ExamStatus]

  /**
   * 不允许录入成绩的考试类型列表
   */
  var emptyScoreStatuses = Collections.newSet[ExamStatus]

  /**
   * 是否提交即发布
   */
  var submitIsPublish: Boolean = false

  def this(project: Project) {
    this()
    endGaElements += new GradeType(GradeType.Usual, "0003", "平时成绩", "Component Score")
    //    endGaElements += new GradeType(GradeType.Middle, "0001", "期中成绩", "Middle Score")
    endGaElements += new GradeType(GradeType.End, "0002", "期末成绩", "Final Exam Score")

    delayGaElements += new GradeType(GradeType.Usual)
    delayGaElements += new GradeType(GradeType.Middle)
    delayGaElements += new GradeType(GradeType.Delay)

    makeupGaElements += new GradeType(GradeType.Makeup)

    allowExamStatuses += new ExamStatus(ExamStatus.Normal)
    allowExamStatuses += new ExamStatus(ExamStatus.Misc)
    emptyScoreStatuses += new ExamStatus(ExamStatus.Absent)
    emptyScoreStatuses += new ExamStatus(ExamStatus.Cheat)
    emptyScoreStatuses += new ExamStatus(ExamStatus.Violation)
    emptyScoreStatuses += new ExamStatus(ExamStatus.Delay)
    emptyScoreStatuses += new ExamStatus(ExamStatus.Misc)
    emptyScoreStatuses += new ExamStatus(ExamStatus.Unqualify)
  }

  def getRemovableElements(gradeType: GradeType): collection.Set[GradeType] = {
    gradeType.id match {
      case GradeType.EndGa => endGaElements
      case GradeType.DelayGa => Set(new GradeType(GradeType.Delay))
      case GradeType.MakeupGa => makeupGaElements
      case _ => Set.empty
    }
  }
}

class GradeTypeConfig {

  var publishable: Boolean = _

  var finalCandinate: Boolean = _

  var gaElement: Boolean = _

  var examType: ExamType = _

  var markStyles = new collection.mutable.ListBuffer[ScoreMarkStyle]

  var precision: Int = _
}
