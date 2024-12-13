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

package org.openurp.code.prac.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean

/** 学生实践课程大类 */
@code("school")
class StdPracticeCategory extends CodeBean

/** 学生实践活动类型
 */
@code("school")
class StdPracticeType extends CodeBean {
  var category: StdPracticeCategory = _
}

/** 实践课程类型 */
@code("school")
class PracActivityType extends CodeBean {
}
