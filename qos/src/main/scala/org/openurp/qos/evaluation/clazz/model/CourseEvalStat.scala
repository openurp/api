/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.qos.evaluation.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.pojo.Updated
import org.beangle.data.model.{LongId, LongIdEntity}
import org.openurp.base.edu.code.model.CourseAssessCategory
import org.openurp.base.edu.model.{Course, Project, Semester, Teacher}
import org.openurp.base.model.Department
import org.openurp.qos.evaluation.model.{AssessGrade, Indicator, Option, Question, Questionnaire}

import scala.collection.mutable

trait Rank {
  /**分类排名*/
  var categoryRank: Int = _

  /**部门排名*/
  var departRank: Int = _

  /**全校排名*/
  var schoolRank: Int = _
}

class CourseEvalStat extends LongId with Updated  with Rank {
  /** 课程序号 */
  var crn: scala.Option[String] = None
  /** 项目 */
  var project: Project = _

  /** 教学日历 */
  var semester: Semester = _

  /** 总得分 */
  var score: Float = _

  /** 是否发布 */
  var publishStatus: Int = _

  /** 有效票数 */
  var tickets: Int = _

  /** 教师 */
  var teacher: Teacher = _

  /** 课程 */
  var course: Course = _

  /** 开课院系 */
  var teachDepart: Department = _

  /** 教师所属院系 */
  var teacherDepart: Department = _

  /** 课程所在学科 */
  var category: CourseAssessCategory = _

  /** 评价等级ABCDEF */
  var grade: AssessGrade = _

  /** 问题类别得分 */
  var indicatorStats = Collections.newBuffer[CourseIndicatorStat]

  /** 问题详细信息统计 */
  var questionStats = Collections.newBuffer[CourseQuestionStat]

  def questions: Iterable[Question] = {
    questionStats.map(_.question)
  }

}

/** 指标统计
 */
class CourseIndicatorStat extends LongId {

  /** 问题类别 */
  var indicator: Indicator = _

  /** 问题类别统计的总分值 */
  var score: Double = _

  /** 对应等级 */
  var grade: AssessGrade = _

  /** 大类中的排名 */
  var categoryRank: Int = _

  /** 问卷评教结果 */
  var stat: CourseEvalStat = _
}

/** 问题统计
 */
class CourseQuestionStat extends LongId {

  var stat: CourseEvalStat = _

  /** 具体问题 */
  var question: Question = _

  /** 平均得分 */
  var score: Double = _

  /** 具体选项 */
  var optionStats: mutable.Buffer[CourseOptionStat] = new collection.mutable.ListBuffer[CourseOptionStat]
}

class CourseOptionStat extends LongId {

  /** 问题统计明细 */
  var questionStat: CourseQuestionStat = _

  /** 选项 */
  var option: Option = _

  /** 人数 */
  var amount: Int = _
}
