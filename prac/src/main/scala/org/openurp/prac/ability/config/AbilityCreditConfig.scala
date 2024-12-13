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

package org.openurp.prac.ability.config

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.InstantRange
import org.openurp.base.model.ProjectBased
import org.openurp.code.edu.model.{EducationLevel, EducationType}

import scala.collection.mutable

/** 能力拓展学分配置
 */
@config
class AbilityCreditConfig extends LongId, InstantRange, ProjectBased {

  /** 培养类型 */
  var eduType: EducationType = _

  /** 培养层次 */
  var levels: mutable.Set[EducationLevel] = Collections.newSet[EducationLevel]

  /** 配置项 */
  var settings: mutable.Buffer[AbilityCreditSetting] = new mutable.ArrayBuffer[AbilityCreditSetting]

  /** 通知 */
  var notice: String = _

  /** 认定的学分 */
  var credits: Int = _
}
