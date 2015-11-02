/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
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
package org.openurp.edu.teach.lesson.model

import org.beangle.data.model.LongId
import org.openurp.edu.teach.lesson.LessonLimitMeta
import org.beangle.data.model.YearId
/**
 * 选课限制条件项
 */
class LessonLimitItem extends LongId with Cloneable with YearId {

  /** 限制具体项目 */
  var meta: LessonLimitMeta.LimitMeta = _

  /** 所在限制组 */
  var group: LessonLimitGroup = _

  /**
   * 是否包含限定内容
   *  不包含exclusive情况下，为排除限定内容
   */
  var inclusive: Boolean = _

  /** 限制内容 */
  var content: String = _

  def year = group.year

}