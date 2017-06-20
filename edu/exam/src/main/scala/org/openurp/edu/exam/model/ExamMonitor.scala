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
package org.openurp.edu.exam.model

import org.beangle.data.model.LongId
import org.openurp.base.model.Department
import org.openurp.people.base.model.Person
import org.openurp.base.model.User
import org.openurp.edu.base.model.Teacher
import org.beangle.data.model.pojo.TemporalOn

/**
 * 监考人员信息
 * 记录监考院系、教师、自定义监考人员
 *
 * @depend - - - User
 * @depend - - - Department
 * @author chaostone
 */
class ExamMonitor extends LongId with TemporalOn {

  var teacher: Teacher = _
  /**
   * 监考院系
   */
  var department: Department = _

  def this(teacher: Teacher, department: Department) {
    this()
    this.teacher = teacher
    this.department = department
  }

}