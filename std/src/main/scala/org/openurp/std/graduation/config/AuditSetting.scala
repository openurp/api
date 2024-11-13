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

package org.openurp.std.graduation.config

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, TemporalOn}
import org.openurp.base.model.ProjectBased
import org.openurp.base.config.model.Rule
import org.beangle.commons.collection.Collections
import org.beangle.data.model.annotation.config
import org.openurp.code.edu.model.EducationLevel

import scala.collection.mutable
/**
 * 毕业审核配置
 */
@config
class AuditSetting extends LongId, ProjectBased, TemporalOn, Remark {
  /** 培养层次集合 */
  var levels: mutable.Set[EducationLevel] = Collections.newSet[EducationLevel]
  /** 毕业审核规则 */
  var grules: mutable.Set[Rule] = Collections.newSet[Rule]
  /** 学位审核规则 */
  var drules: mutable.Set[Rule] = Collections.newSet[Rule]
}
