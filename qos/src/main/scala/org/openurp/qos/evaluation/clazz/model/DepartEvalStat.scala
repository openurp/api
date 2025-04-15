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

package org.openurp.qos.evaluation.clazz.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{Department, Project, Semester}

/** 按照开课院系统计
 *
 */
class DepartEvalStat extends LongId, Updated {

  /** 项目 */
  var project: Project = _

  /** 开课院系 */
  var department: Department = _

  /** 平均分 */
  var avgScore: Double = _

  /** 学年学期 */
  var semester: Semester = _

  /** 课程数量 */
  var courseCount: Int = _

}
