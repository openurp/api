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

package org.openurp.qos.evaluation.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.pojo.Updatable
import org.beangle.data.model.{LongId, LongIdEntity}
import org.openurp.base.edu.model.Course
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Department, Project, Semester}
import org.openurp.code.edu.model.CourseCategory
import org.openurp.qos.evaluation.base.model.{AssessGrade, Indicator, Option, Question, Questionnaire}

import scala.collection.mutable
import scala.compiletime.uninitialized

trait Rank {
  /** 分类排名 */
  var categoryRank: Int = uninitialized

  /** 部门排名 */
  var departRank: Int = uninitialized

  /** 全校排名 */
  var schoolRank: Int = uninitialized
}

class CourseEvalStat extends LongId, Updatable, Rank {
  /** 课程序号 */
  var crn: scala.Option[String] = None
  /** 项目 */
  var project: Project = uninitialized

  /** 教学日历 */
  var semester: Semester = uninitialized

  /** 总得分 */
  var score: Float = uninitialized

  /** 是否发布 */
  var publishStatus: Int = uninitialized

  /** 有效票数 */
  var tickets: Int = uninitialized

  /** 教师 */
  var teacher: Teacher = uninitialized

  /** 课程 */
  var course: Course = uninitialized

  /** 开课院系 */
  var teachDepart: Department = uninitialized

  /** 教师所属院系 */
  var teacherDepart: Department = uninitialized

  /** 课程所在学科 */
  var category: CourseCategory = uninitialized

  /** 评价等级ABCDEF */
  var grade: AssessGrade = uninitialized

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
  var indicator: Indicator = uninitialized

  /** 问题类别统计的总分值 */
  var score: Double = uninitialized

  /** 对应等级 */
  var grade: AssessGrade = uninitialized

  /** 大类中的排名 */
  var categoryRank: Int = uninitialized

  /** 问卷评教结果 */
  var stat: CourseEvalStat = uninitialized
}

/** 问题统计
 */
class CourseQuestionStat extends LongId {

  var stat: CourseEvalStat = uninitialized

  /** 具体问题 */
  var question: Question = uninitialized

  /** 平均得分 */
  var score: Double = uninitialized

  /** 具体选项 */
  var optionStats: mutable.Buffer[CourseOptionStat] = new collection.mutable.ListBuffer[CourseOptionStat]
}

class CourseOptionStat extends LongId {

  /** 问题统计明细 */
  var questionStat: CourseQuestionStat = uninitialized

  /** 选项 */
  var option: Option = uninitialized

  /** 人数 */
  var amount: Int = uninitialized
}
