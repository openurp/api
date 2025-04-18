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

package org.openurp.base.hr.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.TemporalOn
import org.openurp.code.job.model.TutorType

import java.time.LocalDate

/** 导师资格聘任记录
 */
class TutorJournal extends LongId, TemporalOn {
  /** 教职工 */
  var staff: Staff = _

  /** 导师类型 */
  var tutorType: TutorType = _

  def this(staff: Staff, tutorType: TutorType, appointOn: LocalDate) = {
    this()
    this.staff = staff
    this.tutorType = tutorType
    this.beginOn = appointOn
  }
}
