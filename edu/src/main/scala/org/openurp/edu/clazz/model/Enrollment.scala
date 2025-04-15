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

package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.value
import org.beangle.data.model.Component
import org.openurp.base.model.Department
import org.openurp.code.edu.model.CourseAbilityRate
import org.openurp.edu.clazz.model.Enrollment.GenderRatio

class Enrollment extends Cloneable, Component {

  /** 学生所在部门 */
  var depart: Option[Department] = None

  /** 年级 */
  var grades: Option[String] = None

  /** 学生人数 */
  var stdCount: Int = _

  /** 最大人数 */
  var capacity: Int = _

  /**
   * 是否锁定人数上限
   */
  var capacityLocked: Boolean = false

  /** 男女比例 */
  var genderRatio: GenderRatio = GenderRatio.empty

  /**
   * 保留人数<br>
   * 一个任务的真实的人数上限 = capacity - reservedCount
   */
  var reservedCount: Int = _

  /** 上课名单 */
  var courseTakers = Collections.newBuffer[CourseTaker]

  /** 限制条件组 */
  var restrictions = Collections.newBuffer[ClazzRestriction]

  /** 要求课程能力等级 */
  var abilityRates = Collections.newSet[CourseAbilityRate]

  /** 上课名单分组 */
  var subclazzes = Collections.newBuffer[Subclazz]

  /** 是否全校共享 */
  var shared: Boolean = _
}

object Enrollment {

  @value
  class GenderRatio(val value: Short) extends Ordered[GenderRatio], Serializable {
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
