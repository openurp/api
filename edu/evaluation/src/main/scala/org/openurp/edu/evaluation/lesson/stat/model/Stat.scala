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
package org.openurp.edu.evaluation.lesson.stat.model

import java.util.Date
import scala.collection.mutable.Buffer
import org.beangle.data.model.LongId
import org.openurp.base.model.Semester
import org.openurp.edu.evaluation.model.{ Option, Question, QuestionType, Questionnaire }
import org.beangle.data.model.LongIdEntity
import org.beangle.commons.collection.Collections

trait Rank {
  var rank: Int = _

  var departRank: Int = _
}

trait EvalStat extends LongIdEntity {

  /** 教学日历 */
  var semester: Semester = _

  /** 统计时间 */
  var statAt: Date = _

  /** 总得分 */
  var score: Float = _

  /** 有效总分 */
  var validScore: Double = _

  /** 附加题总分 */
  var addScore: Double = _

  /**是否发布*/
  var published: Integer = _

  /** 有效票数 */
  var validTickets: Integer = _

  /**所有样本*/
  var allTickets: Integer = _

  /**问卷*/
  var questionnaire: Questionnaire = _

  /** 问题详细信息统计 */
  var questionStats = Collections.newBuffer[QuestionStat]

  /** 问题类别得分 */
  var questionTypeStats = Collections.newBuffer[QuestionTypeStat]
}

trait QuestionStat extends LongIdEntity {

  var evalStat: EvalStat = _

  /** 具体问题 */
  var question: Question = _

  /** 平均得分 */
  var average: Double = _

  /** 标准差 */
  var stddev: Double = _

  /** 总得分 */
  var total: Double = _

  /** 具体选项 */
  var optionStats: Buffer[OptionStat] = new collection.mutable.ListBuffer[OptionStat]
}

trait QuestionTypeStat extends LongIdEntity {
  /** 问题类别 */
  var questionType: QuestionType = _

  /** 问题类别统计的分值(百分制) */
  var score: Float = _

  /** 问卷评教结果 */
  var evalStat: EvalStat = _

}

trait OptionStat extends LongIdEntity {

  /**问题统计明细*/
  var questionStat: QuestionStat = _

  /**选项*/
  var option: Option = _

  /**人数*/
  var amount: Int = _
}