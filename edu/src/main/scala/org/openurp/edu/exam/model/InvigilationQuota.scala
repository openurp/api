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

package org.openurp.edu.exam.model

import java.time.LocalDate

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.model.{Campus, Department, Project, Semester, User}

/**
 * 监考人员
 * NatureId(teacher,semester)
 *
 * @author chaostone
 */
class InvigilationQuota extends LongId with Remark {

  /** 项目 */
  var project: Project = _

  /** 学年学期 */
  var semester: Semester = _

  /** 教师 */
  var invigilator: User = _

  /** 次数 */
  var amount: Int = _

  /** 监考明细 */
  var details = Collections.newBuffer[InvigilationQuotaDetail]

  /** 排除的日期 */
  var excludes = Collections.newBuffer[LocalDate]

  def addQuota(campus: Campus, depart: Department, amount: Float): InvigilationQuotaDetail = {
    var result: InvigilationQuotaDetail = null
    details find (x => x.campus == campus && x.depart == depart) match {
      case None =>
        val detail = new InvigilationQuotaDetail(campus, depart, amount)
        detail.quota = this
        this.details += detail
        result = detail
      case Some(detail) =>
        detail.amount += amount
        result = detail
    }
    var sum = 0f
    for (iq <- details) {
      sum += iq.amount
    }
    this.amount = Math.round(sum).toInt
    result
  }

  def clearQuota(): Unit = {
    this.amount = 0
    for (iq <- details) {
      iq.amount = 0
    }
  }

  def cleanup(): Boolean = {
    val removed = Collections.newBuffer[InvigilationQuotaDetail]
    var sum = 0d
    for (iq <- details) {
      if (java.lang.Float.compare(0, iq.amount) == 0) {
        removed += iq
      }
      iq.amount = Math.round(iq.amount).toFloat
      sum += iq.amount
    }
    this.amount = sum.toInt
    val oldSize = details.size
    details --= removed
    details.size < oldSize
  }

  def departs: collection.Set[Department] = {
    details.map(_.depart).toSet
  }

  def campuses: collection.Set[Campus] = {
    details.map(_.campus).toSet
  }

  def getCampusQuota(campus: Campus): Int = {
    var sum = 0d
    for (iq <- details) {
      if (iq.campus == campus) sum += iq.amount
    }
    sum.toInt
  }

}
