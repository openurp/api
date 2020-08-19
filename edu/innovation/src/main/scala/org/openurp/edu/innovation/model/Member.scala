/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.innovation.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.edu.base.model.Student

object Member {
  def apply(project: Project, std: Student): Member = {
    val m = new Member()
    m.project = project
    m.std = std
    m.duty = "--"
    m.phone = "--"
    m
  }
}
class Member extends LongId with Remark {

  var std: Student = _

  var project: Project = _

  var duty: String = _

  var hobby: Option[String] = None

  var phone: String = _

  var email: Option[String] = None
}
