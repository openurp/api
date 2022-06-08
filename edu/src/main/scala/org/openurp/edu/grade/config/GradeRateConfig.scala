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

package org.openurp.edu.grade.config

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.openurp.base.model.ProjectBased
import org.openurp.code.edu.model.GradingMode

import java.text.NumberFormat
import scala.collection.mutable

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
  var items: mutable.Buffer[GradeRateItem] =Collections.newBuffer[GradeRateItem]

  /**
   * 及格线
   */
  var passScore: Float = _

  /**
   * 将字符串按照成绩记录方式转换成数字.<br>
   * 空成绩将转换成None
   *
   * @param grade 不能为空
   * @return
   */
  def convert(grade: String): Option[Float] = {
    items.find(_.grade == grade).map(i => i.defaultScore)
  }

  /**
   * 将字符串按照成绩记录方式转换成数字.<br>
   * 空成绩将转换成null
   *
   * @param score 分数
   * @return
   */
  def convert(score: Float): Option[String] = {
    if (gradingMode.numerical) {
      Some(NumberFormat.getInstance.format(score))
    } else {
      items.find(_.contains(score)).map(_.grade)
    }
  }

}
