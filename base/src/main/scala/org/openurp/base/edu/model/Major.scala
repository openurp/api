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
package org.openurp.base.edu.model

import java.time.LocalDate

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Coded
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.pojo.Remark
import org.beangle.data.model.pojo.TemporalOn
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Department
import org.openurp.code.edu.model.DisciplineCategory
import org.openurp.code.edu.model.EducationLevel
import org.openurp.base.edu.ProjectBased

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

  /** 学科信息 */
  var disciplines = Collections.newBuffer[MajorDiscipline]

  /** 建设过程 */
  var journals = Collections.newBuffer[MajorJournal]

  /**学制*/
  var schoolLengths = Collections.newBuffer[SchoolLength]

  /** 培养层次 */
  def levels: Set[EducationLevel] = {
    journals.map(_.level).toSet
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

  def disciplineCode(date: LocalDate): String = {
    disciplines.find(_.contains(date)).map(_.disciplineCode.getOrElse("")).getOrElse("")
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

  def contains(d: LocalDate): Boolean = {
    if (beginOn.isAfter(d)) {
      false
    } else {
      endOn.forall(!d.isAfter(_))
    }
  }
}

/**
 * 专业建设过程
 * @author chaostone
 *
 */
class MajorJournal extends LongId with TemporalOn with Remark {

  /**专业*/
  var major: Major = _

  /**培养层次*/
  var level: EducationLevel = _

  /**部门*/
  var depart: Department = _

}

/**
 * 学制
 */
class SchoolLength extends LongId {
  /**专业*/
  var major: Major = _

  /**培养层次*/
  var level: EducationLevel = _

  /**起始年级*/
  var fromGrade: String = _

  /**结束年级*/
  var toGrade: Option[String] = None

  /**学制*/
  var normal: Float = _

  /**最低学习年限*/
  var minimum: Float = _

  /**最长学习年限*/
  var maximum: Float = _

}
