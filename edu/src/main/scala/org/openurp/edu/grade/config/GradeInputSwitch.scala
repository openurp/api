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
import org.beangle.data.model.pojo.{InstantRange, Remark}
import org.openurp.base.model.{Project, Semester}
import org.openurp.code.edu.model.GradeType

import java.time.Instant

/**
 * 成绩录入开关
 */
@config
class GradeInputSwitch extends LongId, InstantRange, Remark {

  var project: Project = _

  var semester: Semester = _

  /** 允许录入成绩类型 */
  var types = Collections.newSet[GradeType]

  /** 成绩录入验证开关 */
  var needValidate: Boolean = false

  def checkOpen(): Boolean = checkOpen(Instant.now)

  /**
   * 检查该开关是否开放
   *
   * @param date
   * @return
   */
  def checkOpen(date: Instant): Boolean = {
    if null == beginAt || null == endAt then false
    else !(date.isAfter(endAt) || beginAt.isAfter(date))
  }
}
