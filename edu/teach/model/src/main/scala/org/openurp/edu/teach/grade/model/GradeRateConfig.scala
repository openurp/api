/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.teach.grade.model

import java.text.NumberFormat
import scala.collection.mutable.{ Buffer, ListBuffer }
import org.beangle.data.model.annotation.config
import org.beangle.data.model.LongId
import org.openurp.edu.base.ProjectBased
import org.openurp.edu.base.code.model.ScoreMarkStyle

/**
 * 成绩分级配置
 */
@config
class GradeRateConfig extends LongId with ProjectBased {

  /**
   * 成绩记录方式
   */
  var scoreMarkStyle: ScoreMarkStyle = _

  /**
   * 成绩分级配置项
   */
  var items: Buffer[GradeRateItem] = new ListBuffer[GradeRateItem]

  /**
   * 及格线
   */
  var passScore: Float = _

  /**
   * 默认成绩
   */
  @transient
  private var defaultScoreMap = new collection.mutable.HashMap[String, Float]

  /**
   * 将字符串按照成绩记录方式转换成数字.<br>
   * 空成绩将转换成null
   *
   * @param score
   *            不能为空
   * @param markStyle
   * @return
   */
  def convert(grade: String): java.lang.Float = {
    defaultScoreMap.get(grade).asInstanceOf[java.lang.Float]
  }

  /**
   * 将字符串按照成绩记录方式转换成数字.<br>
   * 空成绩将转换成null
   *
   * @param score
   * @param markStyle
   * @return
   */
  def convert(score: java.lang.Float): String = {
    if (null == score) return ""
    if (scoreMarkStyle.numStyle) return NumberFormat.getInstance.format(score.floatValue())
    for (item <- items if item.contains(score)) {
      return item.grade
    }
    ""
  }

  /**
   * 1)将字符串的成绩等级转换成列表<br>
   * 2)将转换映射成map
   */
  def init() {
    var iterator = items.iterator
    while (iterator.hasNext) {
      val item = iterator.next()
      defaultScoreMap.put(item.grade, item.defaultScore)
    }
  }
}
