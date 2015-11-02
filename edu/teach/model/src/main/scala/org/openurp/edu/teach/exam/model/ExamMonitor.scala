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
package org.openurp.edu.teach.exam.model

import org.beangle.data.model.LongId
import org.openurp.base.model.Department
import org.openurp.hr.base.model.Staff

/**
 * 监考信息
 * </p>
 * 记录监考院系、教师、自定义监考人员
 *
 * @depend - - - ExamActivity
 * @depend - - - Person
 * @depend - - - Department
 * @author chaostone
 */
class ExamMonitor extends LongId {

  /**
   * 排考活动
   */
  var examRoom: ExamRoom = _

  /**
   * 监考老师
   */
  var staff: Staff = _

  /**
   * 监考院系
   */
  var department: Department = _

  def this(examRoom: ExamRoom, staff: Staff, department: Department) {
    this()
    this.staff = staff
    this.examRoom = examRoom
    this.department = department
  }

}