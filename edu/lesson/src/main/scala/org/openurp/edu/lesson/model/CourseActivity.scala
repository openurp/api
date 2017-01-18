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

import org.beangle.commons.lang.time.WeekTime
import org.beangle.commons.model.{ LongId, TemporalOn }
import org.openurp.edu.base.Activity
import org.openurp.edu.base.model.Classroom
import org.openurp.edu.base.model.Teacher
/**
 * 教学活动
 * </p>
 * 上课对象是任务对应的教学班学生
 *
 * @has 1..* AssignedTo 1..* Teacher
 * @depend - - - Lesson
 */
class CourseActivity extends LongId with Ordered[CourseActivity] with Activity {

  /** 教学任务 */
  var lesson: Lesson = _

  /** 上课时间 */
  var time: WeekTime = _

  /** 授课教师列表 */
  var teachers: collection.mutable.Set[Teacher] = _

  /** 教室列表 */
  var rooms: collection.mutable.Set[Classroom] = _

  /** 排课备注 */
  var remark: Option[String] = None

  override def compare(other: CourseActivity): Int = {
    //fix me
    -1
  }

  override def startOn: java.sql.Date = {
    if (null != time) time.startOn else null
  }
}