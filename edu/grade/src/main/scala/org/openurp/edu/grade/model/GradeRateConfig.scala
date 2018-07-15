/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.model

import java.text.NumberFormat

import scala.collection.mutable.{ Buffer, ListBuffer }

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.openurp.edu.base.ProjectBased
import org.openurp.edu.base.code.model.GradingMode

/**
 * 成绩分级配置
 */
@config
class GradeRateConfig extends LongId with ProjectBased {

  /**
   * 成绩记录方式
   */
  var gradingMode: GradingMode = _

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
  private var defaultScoreMap = Collections.newMap[String, Float]

  /**
   * 将字符串按照成绩记录方式转换成数字.<br>
   * 空成绩将转换成null
   *
   * @param score
   *            不能为空
   * @param gradingMode
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
   * @param gradingMode
   * @return
   */
  def convert(score: java.lang.Float): String = {
    if (null == score) return ""
    if (gradingMode.numerical) return NumberFormat.getInstance.format(score.floatValue())
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
