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

import org.beangle.commons.lang.time.WeekTime
import org.beangle.data.model.LongId
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.User

/** 小课上课安排
 */
class MiniClazzActivity extends LongId {

  /** 教学任务 */
  var miniClazz: MiniClazz = _

  /** 上课时间 */
  var time: WeekTime = _

  /** 开始节次 */
  var beginUnit: Short = _

  /** 结束节次 */
  var endUnit: Short = _

  /** 授课教师 */
  var teacher: Option[Teacher] = None

  /** 辅导老师1 */
  var advisor1: Option[User] = None

  /** 辅导老师2 */
  var advisor2: Option[User] = None
}
