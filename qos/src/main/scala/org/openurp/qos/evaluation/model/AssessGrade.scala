/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.qos.evaluation.model

import org.beangle.data.model.pojo.Named
import org.beangle.data.model.{IntId, LongId}
import org.openurp.base.edu.model.Project

class AssessGrade extends IntId with Named with Ordered[AssessGrade] {
  /** 最小分值 */
  var minScore: Float = _

  /** 最大分值 */
  var maxScore: Float = _

  var grade: Int = _

  var description: String = _

  override def compare(that: AssessGrade): Int = {
    grade - that.grade
  }

  /** 评价 */
  var criteria: AssessCriteria = _

}
