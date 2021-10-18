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

package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.value
import org.beangle.data.model.Component
import org.openurp.base.edu.code.model.CourseAbilityRate
import org.openurp.base.model.Department
import org.openurp.edu.clazz.model.Enrollment.GenderRatio

class Enrollment extends Cloneable with Component {

  /** 学生所在部门 */
  var depart: Option[Department] = None

  /** 年级 */
  var grade: Option[String] = None

  /** 学生人数 */
  var actual: Int = _

  /** 最大人数 */
  var capacity: Int = _

  /**
   * 是否锁定人数上限
   */
  var locked: Boolean = false

  /** 男女比例 */
  var genderRatio: GenderRatio = GenderRatio.empty

  /**
   * 保留人数<br>
   * 一个任务的真实的人数上限 = capacity - reserved
   */
  var reserved: Int = _

  /** 上课名单 */
  var courseTakers = Collections.newBuffer[CourseTaker]

  /** 限制条件组 */
  var restrictions = Collections.newBuffer[Restriction]

  /** 要求课程能力等级 */
  var abilityRates = Collections.newSet[CourseAbilityRate]

  /** 上课名单分组 */
  var subclazzes = Collections.newBuffer[Subclazz]
}

object Enrollment {

  @value
  class GenderRatio(val value: Short) extends Ordered[GenderRatio] with Serializable {
    override def compare(other: GenderRatio): Int = {
      if (this.value < other.value) -1
      else if (this.value == other.value) 0
      else 1
    }
  }

  object GenderRatio {
    val empty = new GenderRatio(0)
  }

}
