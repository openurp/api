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
package org.openurp.code.std.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean

@code("industry")
class UnregisteredReason extends CodeBean

@code("industry")
class UncheckinReason extends CodeBean
/**
 * 学籍异动类别
 * 参见教育部标准JY/T 1001 4.2.39
 */
@code("industry")
class StdAlterType extends CodeBean

/**
 * 学籍异动原因
 * 参见教育部标准JY/T 1001 4.2.40
 */
@code("industry")
class StdAlterReason extends CodeBean

/**
 * 学生处分类型
 * 参见教育部标准JY/T 1001 4.2.3
 */
@code("industry")
class StdPunishmentType extends CodeBean

/**
 * 学生当前状态
 * 参见教育部标准JY/T 1001 4.2.42
 */
@code("industry")
class StudentStatus extends CodeBean

/**
 * 高考科目
 * 参见教育部标准JY/T 1001 4.2.8
 */
@code("industry")
class UeeSubjectType extends CodeBean

/**
 * 费用来源
 */
@code("industry")
class FeeOrigin extends CodeBean

/**毕业去向*/
@code("industry")
class WheretoGo extends CodeBean
