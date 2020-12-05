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
package org.openurp.prac.innovation.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, TemporalOn}
import org.openurp.base.model.Department
import org.openurp.code.edu.model.Discipline
import org.openurp.base.edu.model.Teacher

import scala.collection.mutable

/**
 * 项目
 */
class Project extends LongId with TemporalOn with Remark {

  /** 项目编号 */
  var code: Option[String] = _

  /** 批次 */
  var batch: Batch = _

  /** 项目名称 */
  var title: String = _

  /** 成员 */
  var members = Collections.newBuffer[Member]

  /** 材料 */
  var materials = Collections.newBuffer[Material]

  /** 院系 */
  var department: Department = _

  /** 申请人 */
  var manager: Option[Member] = None

  /** 项目级别 */
  var level: ProjectLevel = _

  /** 等级记录 */
  var levels: mutable.Buffer[LevelJounal] = Collections.newBuffer[LevelJounal]

  /** 项目类型 */
  var category: ProjectCategory = _

  /** 学科 */
  var discipline: Discipline = _

  /** 指导老师 */
  var instructors = Collections.newBuffer[Teacher]

  /** 状态 */
  var state: ProjectState = _

  /** 简介 */
  var intro: Option[Intro] = None

  /** 经费 */
  var funds: Int = _

  def closureMaterial: Option[Material] = {
    materials.find(_.stageType.id == StageType.Closure)
  }

}
