/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.model

import java.text.NumberFormat

import scala.collection.mutable.Buffer
import scala.collection.mutable.ListBuffer

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.openurp.code.edu.model.GradingMode
import org.openurp.edu.base.ProjectBased

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
   * 将字符串按照成绩记录方式转换成数字.<br>
   * 空成绩将转换成null
   *
   * @param score
   *            不能为空
   * @param gradingMode
   * @return
   */
  def convert(grade: String): Option[Float] = {
    items.find(_.grade == grade).map(i => i.defaultScore)
  }

  /**
   * 将字符串按照成绩记录方式转换成数字.<br>
   * 空成绩将转换成null
   *
   * @param score
   * @param gradingMode
   * @return
   */
  def convert(score: Float): String = {
    if (gradingMode.numerical) {
      NumberFormat.getInstance.format(score)
    } else {
      items.find(_.contains(score)) match {
        case None    => ""
        case Some(g) => g.grade
      }
    }
  }

}
