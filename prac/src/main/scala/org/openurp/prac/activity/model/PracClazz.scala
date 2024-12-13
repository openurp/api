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

import org.openurp.base.edu.model.Course
import org.openurp.code.edu.model.{CourseType, TeachLangType}

/**
 * 校内实践课程
 */
class PracClazz extends AbstractPracActivity {
  /** 课程名称 */
  var courseName: String = _
  /** 对应课程 */
  var course: Option[Course] = None
  /** 课程类别 */
  var courseType: Option[CourseType] = None
  /** 班级名称 */
  var clazzName: String = _
  /** 授课语言 */
  var langType: TeachLangType = _

  def addSchedule(ns: AbstractPracSchedule): Unit = {
    this.schedules += ns
    ns.asInstanceOf[PracClazzSchedule].activity = this
  }
}
