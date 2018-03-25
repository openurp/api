/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.base.code.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean
import org.beangle.data.model.pojo.Hierarchical

/**
 * 用户分类
 */
@code("school")
class UserCategory extends CodeBean

/**
 * 部门分类
 */
@code("school")
class DepartmentCategory extends CodeBean with Hierarchical[DepartmentCategory]

/**
 * 上下午时段
 */
@code("school")
class DayPart extends CodeBean
