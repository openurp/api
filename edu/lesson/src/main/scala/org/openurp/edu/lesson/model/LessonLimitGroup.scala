/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.lesson.model

import org.beangle.commons.model.LongId
import org.beangle.commons.model.YearId
import org.beangle.commons.collection.Collections

/**
 * 课程限制条件组
 */
class LessonLimitGroup extends LongId with Cloneable with YearId {

  /** 教学任务 */
  var lesson: Lesson = _

  /** 条件列表 */
  var items = Collections.newBuffer[LessonLimitItem]

  /** 最大人数 */
  var maxCount: Int = _

  /** 当前人数 */
  var curCount: Int = _

  /**授课对象还是选课对象*/
  var forClass: Boolean = true

  /** 父级菜单 */
  var parent: Option[LessonLimitGroup] = None

  var children = Collections.newBuffer[LessonLimitGroup]

  def year: Int = lesson.year
}
