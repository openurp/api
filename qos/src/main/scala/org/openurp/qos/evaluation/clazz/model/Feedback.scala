/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.qos.evaluation.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.{Course, Semester, Student, Teacher}
import org.openurp.base.model.Department
import org.openurp.edu.clazz.model.Clazz

import java.time.Instant

/**
 * 开放式文字评教
 */
class Feedback extends LongId with Updated {
  /** 课程序号 */
  var crn: String = _
  /** 教学任务 */
  var course: Course = _
  /** 教学日历 */
  var semester: Semester = _
  /** 教师 */
  var teacher: Teacher = _
  /** 开课院系 */
  var teachDepart: Department = _
  /** 学生 */
  var std: Student = _
  /** 评教内容 */
  var contents: String = _
  /** 评价等级 */
  var grade: String = _
}
