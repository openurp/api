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

package org.openurp.qos.evaluation.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.*
import org.openurp.base.edu.model.Project

/**
 * 问题类型
 * 指示器
 *
 * @author cwx,chaostone
 *
 */
class Indicator extends LongId with Coded with Named with Updated with TemporalOn with Remark with Ordered[Indicator] {
  var project: Project = _
  /** 英文名称 */
  var enName: scala.Option[String] = None

  override def compare(that: Indicator): Int = {
    code.compare(that.code)
  }
}
