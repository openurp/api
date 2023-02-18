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

package org.openurp.degree.thesis.model

import org.beangle.data.model.LongId
import org.openurp.base.edu.model.TeachingOffice
import org.openurp.base.model.{Department, User}

import java.time.Instant
import scala.collection.mutable

/**
 * 答辩组
 */
class DefenseGroup extends LongId {

  var idx: Int = _

  var department: Department = _

  var office: Option[TeachingOffice] = None

  var place: Option[String] = None

  var beginAt: Option[Instant] = None

  var endAt: Option[Instant] = None

  var secretary: Option[User] = None

  var members: mutable.Buffer[DefenseMember] = new mutable.ArrayBuffer[DefenseMember]

  var writers: mutable.Set[Writer] = new mutable.HashSet[Writer]

  var notices: mutable.Buffer[DefenseNotice] = new mutable.ArrayBuffer[DefenseNotice]

  def staffCount: Int = members.size + secretary.size

  def orderedWriters: mutable.Buffer[Writer] = {
    writers.toBuffer.sortBy(x => x.advisor.get.user.code + "_" + x.code)
  }
}
