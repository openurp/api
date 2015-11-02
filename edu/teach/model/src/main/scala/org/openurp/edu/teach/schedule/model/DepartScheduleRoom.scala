/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.teach.schedule.model

import org.beangle.data.model.LongId
import org.openurp.base.model.Department
import org.openurp.edu.base.ProjectBased
import org.openurp.edu.base.model.Classroom
/**
 * 项目教室关系数据
 *
 * @author chaostone
 */

class DepartScheduleRoom extends LongId  with ProjectBased {
  /** 教室 */
  var classroom: Classroom = _
  /** 固定使用部门 */
  var departs: collection.mutable.Set[Department] = new collection.mutable.HashSet[Department]
  /** 保留时间 */
  //  var reserved:List[TimeUnit]=_
} 