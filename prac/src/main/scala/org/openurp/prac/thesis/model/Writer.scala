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

package org.openurp.prac.thesis.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Named
import org.openurp.base.edu.model.Major
import org.openurp.base.model.{Department, User}
import org.openurp.base.std.model.GraduateGrade

import java.time.Instant
import scala.collection.mutable

class Writer extends LongId  {

  var graduateGrade: GraduateGrade = _

  var user: User = _

  var department: Department = _

  /** 指导老师 */
  var advisor: Option[Advisor] = None

  /** 论文题目 */
  var subject: Option[Subject] = None

    var squad: Option[String] = None

  var deadlines: mutable.Buffer[Deadline] = new mutable.ArrayBuffer[Deadline]

  def getOrCreateDeadline(stage: Stage): Deadline = {
    deadlines.find(x => x.stage == stage) match {
      case Some(dl) => dl
      case None => Deadline(stage, this)
    }
  }

  def getSubmitAt(stage: Stage): Option[Instant] = {
    deadlines.find(x => x.stage == stage) match {
      case Some(dl) => dl.submitAt
      case None => None
    }
  }

  def getEndAt(stage: Stage): Option[Instant] = {
    deadlines.find(x => x.stage == stage) match {
      case Some(dl) => dl.endAt
      case None => None
    }
  }

}
