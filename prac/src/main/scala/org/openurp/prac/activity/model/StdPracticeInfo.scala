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

package org.openurp.prac.activity.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Named, Remark, Updated}
import org.openurp.base.std.model.Student
import org.openurp.code.prac.model.{StdPracticeCategory, StdPracticeType}

/** 学生实践活动
 */
class StdPracticeInfo extends LongId, Named, Updated, Remark {

  var std: Student = _

  /** 实践课程大类 */
  var category: StdPracticeCategory = _

  /** 实践课程类型 */
  var practiceType: StdPracticeType = _

  /** 学时数 */
  var hours: Int = _

  /** 地点 */
  var place: Option[String] = None

  /** 日期时间 */
  var datetime: Option[String] = None
}
