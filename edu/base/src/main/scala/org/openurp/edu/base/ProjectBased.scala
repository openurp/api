/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2016, OpenURP Software.
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
package org.openurp.edu.base

import org.openurp.edu.base.model.Project

/**
 * 教学项目
 * </p>
 * 教学项目是指本科教学、研究生教学、辅修教学等教学项目。项目本身主要用于管理学校具备独立运作、具备较强隔离性的教学项目。
 * 他是按照EAMS核心业务范围建立的顶级概念。
 * 项目可以简要描述为：在1）固定的部门和授课团队等师资下，培养2）固定学历层次的学生，培养学生可以按照分类标签进行划分；
 * 具体培养过程中可以使用3）固定的校区和稳定的教学校历，这种过程和资源的总称为项目。
 *
 * @author chaostone
 * @since 3.0.0
 */
trait ProjectBased {

  var project: Project = _
}
