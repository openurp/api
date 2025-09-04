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

package org.openurp.base.std.model

import org.beangle.data.model.LongId
import org.openurp.base.hr.model.Teacher

/** 学生导师
 */
class StudentTutor extends LongId {

  var std: Student = _

  var tutor: Teacher = _

  var tutorship: Tutorship = Tutorship.Major

  def this(std: Student, tutor: Teacher, tutorship: Tutorship) = {
    this()
    this.std = std
    this.tutor = tutor
    this.tutorship = tutorship
  }

  def name: String = {
    tutor.name
  }
}

enum Tutorship(val id: Int, val name: String) {
  case Major extends Tutorship(1, "学业导师")
  case Thesis extends Tutorship(2, "论文指导导师")
}
