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

package org.openurp.qos.evaluation.base.model

import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.{IntId, LongId}
import org.openurp.base.model.Project

/** 评价等级
 *
 */
class AssessGrade extends IntId, Named, Ordered[AssessGrade] {
  /** 最小分值 */
  var minScore: Float = _

  /** 最大分值 */
  var maxScore: Float = _

  /** 等级数字值 */
  var grade: Int = _

  /** 描述 */
  var description: String = _

  /** 评价 */
  var criteria: AssessCriteria = _

  override def compare(that: AssessGrade): Int = {
    grade - that.grade
  }

}
