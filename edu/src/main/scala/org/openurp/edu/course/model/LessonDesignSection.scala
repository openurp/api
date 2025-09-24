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

/** 课程设计教学环节
 */
class LessonDesignSection extends LongId {

  /** 序号(从1开始) */
  var idx: Int = _

  /** 授课内容 */
  var design: LessonDesign = _

  /** 标题 */
  var title: String = _

  /** 教学内容提要 */
  var summary: String = _

  /** 分钟 */
  var duration: Int = _

  /** 教学过程设计 */
  var details: String = _

  def this(design: LessonDesign, idx: Int, title: String, duration: Int, summary: String, details: String) = {
    this()
    this.design = design
    this.idx = idx
    this.title = title
    this.summary = summary
    this.duration = duration
    this.details = details
  }

}
