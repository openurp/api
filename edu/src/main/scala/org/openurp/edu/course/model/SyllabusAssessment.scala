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
import org.openurp.code.edu.model.GradeType

/** 教学大纲-成绩评分比例
 */
class SyllabusAssessment extends LongId {

  var syllabus: Syllabus = _

  /** 序号(从1开始) */
  var idx: Int = _

  /** 成绩类型 */
  var gradeType: GradeType = _

  /** 小项 */
  var component: Option[String] = None

  /** 考核次数 */
  var assessCount: Int = _

  /** 百分比 */
  var scorePercent: Int = _

  /** 对应课程目标的支撑比例 */
  var objectivePercents: Option[String] = None

  /** 具体办法 */
  var description: Option[String] = None

  /** 评分表 */
  var scoreTable: Option[String] = None

  def this(syllabus: Syllabus, gradeType: GradeType, componentName: Option[String]) = {
    this()
    this.syllabus = syllabus
    this.gradeType = gradeType
    this.component = componentName
  }

  def updateObjectivePercents(percents: Map[String, Int]): Unit = {
    if percents.isEmpty then objectivePercents = None
    else objectivePercents = Some(percents.map(x => s"${x._1}:${x._2}").mkString(","))
  }

  def objectivePercentMap: Map[String, Int] = {
    val map = Collections.newMap[String, Int]
    objectivePercents.foreach { str =>
      val lines = str.split(",")
      lines.foreach { line =>
        val kv = line.split(":")
        map += (kv(0) -> kv(1).toInt)
      }
    }
    map.toMap
  }

  def updateScoreTable(header: String, caption: String): Option[String] = {
    scoreTable match
      case None => None
      case Some(s) => Some(s.replaceAll("<table(.*?)>", header + caption))
  }

}
