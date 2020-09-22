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

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.pojo.Updated

/** 项目材料
 */
class Material extends LongId with Updated {
  var project: Project = _
  var stageType: StageType = _
  /**文件名*/
  var fileName: String = _
  /**大小*/
  var fileSize: Int = _
  /**sha*/
  var sha: String = _
  /**路径*/
  var filePath: String = _

  def this(project: Project, stageType: StageType) {
    this()
    this.project = project
    this.stageType = stageType
    project.materials += (this)
  }
}
