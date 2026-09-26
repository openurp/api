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
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updatable
import org.openurp.base.model.{Project, Semester}
import org.openurp.code.edu.model.CourseCategory
import org.openurp.qos.evaluation.base.model.{AssessGrade, Indicator}

import scala.collection.mutable
import scala.compiletime.uninitialized

/** 分课程大类统计
 *
 */
class CategoryEvalStat extends LongId, Updatable {
  var project: Project = uninitialized

  /** 教学日历 */
  var semester: Semester = uninitialized

  /** 课程分类 */
  var category: CourseCategory = uninitialized

  /** 课程数量 */
  var courseCount: Int = uninitialized

  /** 按照维度分段统计 */
  var ranges: mutable.Buffer[CategoryStatRange] = Collections.newBuffer[CategoryStatRange]

  /** 按照维度分等级统计 */
  var grades: mutable.Buffer[CategoryStatGrade] = Collections.newBuffer[CategoryStatGrade]

  /** 查询某个维度的分段统计结果
   *
   * @param indicator
   * @return
   */
  def getRanges(indicator: Indicator): collection.Seq[CategoryStatRange] = {
    val indicatorRanges = ranges.filter(x => x.indicator == indicator)
    indicatorRanges.sortBy(_.fromScore)
  }

  /** 查询某个维度上所有等级的统计结果
   *
   * @param indicator
   * @return
   */
  def getGrades(indicator: Indicator): collection.Seq[CategoryStatGrade] = {
    val indicatorGrades = grades.filter(x => x.indicator == indicator)
    indicatorGrades.sortBy(_.grade.grade)
  }

  def getIndicators(grade: AssessGrade): collection.Seq[CategoryStatGrade] = {
    val indicatorGrades = grades.filter(x => x.grade == grade)
    indicatorGrades.sortBy(_.indicator.code)
  }

  def indicatorList: collection.Seq[Indicator] = {
    grades.map(_.indicator).toSet.toSeq.sorted
  }

  def gradeList: collection.Seq[AssessGrade] = {
    grades.map(_.grade).toSet.toSeq.sorted
  }

}

/** 分类指标级别统计
 * */
class CategoryStatGrade extends LongId {

  var indicator: Indicator = uninitialized

  var grade: AssessGrade = uninitialized

  var courseCount: Int = uninitialized

  var avgScore: Double = uninitialized

  var maxScore: Double = uninitialized

  var minScore: Double = uninitialized

  var stat: CategoryEvalStat = uninitialized
}

/**
 * 指标分段统计
 */
class CategoryStatRange extends LongId {

  var stat: CategoryEvalStat = uninitialized

  var indicator: Indicator = uninitialized

  var fromScore: Double = uninitialized

  var toScore: Double = uninitialized

  var courseCount: Int = uninitialized

  def contains(v: Double): Boolean = {
    java.lang.Double.compare(fromScore, v) <= 0 && java.lang.Double.compare(v, toScore) < 0
  }

}
