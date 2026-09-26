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

package org.openurp.edu.textbook.model

import org.beangle.data.model.LongId
import org.openurp.edu.clazz.model.Clazz
import org.openurp.base.edu.model.Textbook
import java.time.Instant
import scala.compiletime.uninitialized

/**
 * 教学任务教材统计
 */
class ClazzBookStat extends LongId {

  /**教学任务*/
  var clazz: Clazz = uninitialized

  /**教材*/
  var textbook: Textbook = uninitialized

  /**学生用户量*/
  var stdCount: Int = uninitialized

  /**教师用户量*/
  var teacherCount: Int = uninitialized

  /**统计时间*/
  var statAt: Instant = uninitialized
}
