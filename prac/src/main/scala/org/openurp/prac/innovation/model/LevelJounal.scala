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

package org.openurp.prac.innovation.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updatable

import java.time.Instant
import scala.compiletime.uninitialized

/** 项目的等级记录
 *
 */
class LevelJounal extends LongId, Updatable {

  /** 年度 */
  var awardYear: Int = uninitialized

  var project: Project = uninitialized

  var level: ProjectLevel = uninitialized

  def this(year: Int, project: Project, level: ProjectLevel) = {
    this()
    this.awardYear = year
    this.project = project
    this.level = level
    this.updatedAt = Instant.now
  }
}
