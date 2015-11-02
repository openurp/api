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
package org.openurp.code.hr.model

import org.beangle.data.model.annotation.code
import org.openurp.code.BaseCodeBean
import org.beangle.commons.collection.Collections

/**
 * 教职工类别
 * 参见教育部标准JY/T 1001 4.4.16
 */
@code("industry")
class StaffType extends BaseCodeBean {
  var parent: StaffType = _
  var children = Collections.newBuffer[StaffType]
}
/**
 * 教职工来源
 * 参见教育部标准JY/T 1001 4.4.17
 */
@code("industry")
class StaffSourceType extends BaseCodeBean {
  var parent: StaffSourceType = _
  var children = Collections.newBuffer[StaffSourceType]
}

/**
 * 在职状态
 *
 * 参见教育部标准JY/T 1001 4.4.15
 */
@code("industry")
class WorkStatus extends BaseCodeBean

/**
 * 导师类型
 * 参见教育部标准JY/T 1001 4.4.4
 */
@code("industry")
class TutorType extends BaseCodeBean

