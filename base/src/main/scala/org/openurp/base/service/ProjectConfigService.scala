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

package org.openurp.base.service

import org.openurp.base.model.Project

import scala.reflect.ClassTag

/** 项目属性提供服务
 */
trait ProjectConfigService {

  def get[T](project: Project, name: String, defaultValue: T): T

  def get[T: ClassTag](project: Project, feature: Feature): T

  def getInt(project: Project, f: Feature): Int

  def getString(project: Project, f: Feature): String

  def getBoolean(project: Project, f: Feature): Boolean

  def getFloat(project: Project, f: Feature): Float

  def getDouble(project: Project, f: Feature): Double
}
