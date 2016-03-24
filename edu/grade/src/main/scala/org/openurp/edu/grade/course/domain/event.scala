/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.course.domain

import org.beangle.commons.event.BusinessEvent
import org.openurp.edu.grade.course.model.CourseGrade
import org.openurp.edu.lesson.model.Lesson
/**
 * 已发布成绩变化事件(由于已发布成绩发生变化如修改申请或加分等引起的成绩改变的事件)
 */
class CourseGradeModifyEvent(source: Seq[CourseGrade]) extends BusinessEvent(source)
/**
 * 成绩提交事件
 */
class CourseGradeSubmitEvent(source: Lesson) extends BusinessEvent(source)
