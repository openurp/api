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

package org.openurp.std.alter.config

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.openurp.base.model.Project
import org.openurp.code.std.model.{StdAlterType, StudentStatus}
import scala.compiletime.uninitialized

/** 学籍异动配置
 *
 */
@config
class AlterConfig extends LongId {

  var project: Project = uninitialized

  var alterType: StdAlterType = uninitialized

  var status: StudentStatus = uninitialized

  var alterEndOn: Boolean = uninitialized

  var alterGraduateOn: Boolean = uninitialized

  var inschool: Boolean = uninitialized

  var attributes: String = uninitialized
}
