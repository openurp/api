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
package org.openurp.edu.exam.model

import java.time.LocalDate

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.openurp.base.model.Campus
import org.openurp.base.model.Department
import org.openurp.base.model.User
import org.openurp.edu.base.model.Project
import org.openurp.edu.base.model.Semester
import org.beangle.data.model.pojo.Remark

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
  var quota: Int = _

  /**监考明细*/
  var details = Collections.newBuffer[InvigilationQuotaDetail]

  /** 排除的日期 */
  var excludes = Collections.newBuffer[LocalDate]

  def addQuota(campus: Campus, depart: Department, quota: Float): InvigilationQuotaDetail = {
    var result: InvigilationQuotaDetail = null
    details find (x => x.campus == campus && x.depart == depart) match {
      case None =>
        val detail = new InvigilationQuotaDetail(campus, depart, quota)
        detail.invigilationQuota = this
        this.details += detail
        result = detail
      case Some(detail) =>
        detail.quota += quota
        result = detail
    }
    var sum = 0f
    for (iq <- details) {
      sum += iq.quota
    }
    this.quota = new java.lang.Double(Math.round(sum)).intValue
    result
  }

  def clearQuota() {
    this.quota = 0
    for (iq <- details) {
      iq.quota = 0
    }
  }

  def cleanup(): Boolean = {
    val removed = Collections.newBuffer[InvigilationQuotaDetail]
    var sum = 0d
    for (iq <- details) {
      if (java.lang.Float.compare(0, iq.quota) == 0) {
        removed += iq
      }
      iq.quota = new java.lang.Double(Math.round(iq.quota)).intValue
      sum += iq.quota
    }
    this.quota = new java.lang.Double(sum).intValue()
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
      if (iq.campus == campus) sum += iq.quota
    }
    new java.lang.Double(sum).intValue
  }

}
