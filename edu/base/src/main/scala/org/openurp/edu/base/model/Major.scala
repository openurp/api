/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.base.model

import java.time.LocalDate

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.Coded
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.pojo.Remark
import org.beangle.data.model.pojo.TemporalOn
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Department
import org.openurp.code.edu.model.DisciplineCategory
import org.openurp.edu.base.ProjectBased
import org.openurp.edu.base.code.model.EduSpan

/**
 * 专业
 *
 * @author hs
 */
class Major extends LongId with ProjectBased with TemporalOn with Updated with Coded with Named with Remark {

  /** 专业英文名 */
  var enName: Option[String] = None

  /** 简称 */
  var shortName: Option[String] = None

  /** 专业方向 */
  var directions = Collections.newSet[Direction]

  /** 历史学科信息 */
  var disciplines = Collections.newBuffer[MajorDiscipline]

  /** 建设过程 */
  var journals = Collections.newBuffer[MajorJournal]

  /** 培养层次 */
  def spans: Set[EduSpan] = {
    journals.map(_.span).toSet
  }

  def departments: Set[Department] = {
    journals.map(_.depart).toSet
  }

  def departmentsNow: Set[Department] = {
    departments(LocalDate.now)
  }

  def departments(date: LocalDate): Set[Department] = {
    journals.filter(j => date.isAfter(j.beginOn) && (None == j.endOn || date.isBefore(j.endOn.get)))
      .map(_.depart).toSet
  }
}

/**
 * 专业学科信息
 */
class MajorDiscipline extends LongId with TemporalOn {

  /**专业*/
  var major: Major = _

  /** 学科门类 */
  var category: DisciplineCategory = _

  /** 教育部代码 */
  var disciplineCode: Option[String] = None
}

/**
 * 专业建设过程
 * @author chaostone
 *
 */
class MajorJournal extends LongId with TemporalOn with Remark {

  /**专业*/
  var major: Major = _

  /** 修读年限 */
  var duration: Float = _

  /**培养层次*/
  var span: EduSpan = _

  /**部门*/
  var depart: Department = _

}

class MajorCampus extends LongId {

  /** 年级,形式为yyyy-p */
  var grade:String=_

  /** 专业 */
  var major:Major=_

  /** 对应学期 */
  var terms:Terms=_

  var level:EducationLevel=_
}
