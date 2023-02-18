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

package org.openurp.base.service

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.{Numbers, Strings}
import org.beangle.commons.logging.Logging
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.model.{Project, Semester}
import org.openurp.base.std.model.Grade

import java.time.LocalDate

/**
 * 计算相对学期的工具类
 */
object TermCalculator {
  private val termMap = Collections.newMap[String, Set[Int]]

  /**
   * 判断给定的学期是否在学期字符串内
   *
   * @param termStr
   * @param term
   * @return
   */
  def contains(termStr: String, term: Int): Boolean = {
    if (Strings.contains(termStr, "*")) {
      return true
    }
    termMap.get(termStr) match {
      case Some(s) => s.contains(term)
      case None =>
        val terms = Strings.split(termStr, ",")
        val newTermSet = Collections.newSet[Int]
        for (one <- terms) {
          newTermSet.addOne(Numbers.toInt(one))
        }
        termMap.put(termStr, newTermSet.toSet)
        newTermSet.contains(term)
    }
  }

  val autumn = Set(1, 3, 5, 7, 9, 11)
  val spring = Set(2, 3, 4, 8, 10, 12)
  val all = Set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
  termMap.put("春", spring)
  termMap.put("秋", autumn)
  termMap.put("春季", spring)
  termMap.put("春秋", all)
  termMap.put("春,秋", all)

}

class TermCalculator(project: Project, semester: Semester, entityDao: EntityDao) extends Logging {
  private val termCalcCache = Collections.newMap[LocalDate, Int]

  /**
   * 获得给定日期是给定日期的之后的第几个学期<br>
   * 然后计算这个学期和semester之间的差距，算出学期次序数（从1开始）
   *
   * @param begOn
   * @param omitSmallTerm
   * @return
   */
  private def getTerm(beginOn: LocalDate, omitSmallTerm: Boolean): Int = {
    termCalcCache.get(beginOn) match {
      case Some(term) => term
      case None =>
        val builder = OqlBuilder.from[Number](classOf[Semester].getName, "semester")
          .where("semester.calendar =:calendar", project.calendar)
        builder.where("semester.beginOn between :beginOn and :endOn", beginOn, semester.beginOn)
        builder.select("count(*)")
        val term = entityDao.search(builder).head.intValue()
        termCalcCache.put(beginOn, term)
        term
    }
  }

  def getTerm(grade: Grade, omitSmallTerm: Boolean): Int = {
    getTerm(grade.beginOn.minusDays(10), omitSmallTerm)
  }
}
