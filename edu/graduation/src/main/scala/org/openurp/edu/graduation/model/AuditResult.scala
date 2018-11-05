/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
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
package org.openurp.edu.graduation.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.edu.base.model.Student

class AuditResult extends LongId with Updated {

  /** 所属的毕业审核批次 */
  var session: GraduateSession = _

  /**学生*/
  var std: Student = _

  /** GPA */
  var gpa: Float = _

  /**平均分*/
  var ga: Float = _

  /** 获得学分 */
  var acquiredCredits: Float = _

  /** 要求学分 */
  var requiredCredits: Float = _

  /** 修读学分 */
  var electedCredits: Float = _

  /** 毕业信息*/
  var graduate: GraduateResult = _

  /** 学位信息 */
  var degree: DegreeResult = _
}
