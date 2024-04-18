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

package org.openurp.edu.course.model

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.TeachingNature

/** 大纲课程安排的学时分布
 */
class SyllabusTopicHour extends LongId {
  var topic: SyllabusTopic = _
  /** 学时 */
  var creditHours: Int = _
  /** 实践周 */
  var weeks: Int = _
  /** 课时分类 */
  var nature: TeachingNature = _

  def this(topic: SyllabusTopic, nature: TeachingNature, creditHours: Int, weeks: Int) = {
    this()
    this.topic = topic
    this.nature = nature
    this.creditHours = creditHours
    this.weeks = weeks
  }
}
