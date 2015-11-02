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
package org.openurp.code.person.model

import org.beangle.data.model.annotation.code
import org.openurp.code.BaseCodeBean

/**
 * 血型
 * 参见教育部标准JY/T 1001 4.5.14
 */
@code("industry")
class BloodType extends BaseCodeBean

/**
 * 困难原因
 * 参见教育部标准JY/T 1001 4.2.23
 */
@code("industry")
class DifficultyCause extends BaseCodeBean 

/**
 * 困难程度
 * 参见教育部标准JY/T 1001 4.2.22
 */
@code("industry")
class DifficultyDegree extends BaseCodeBean

/**
 * 家庭类别
 * 参见教育部标准JY/T 1001 4.2.19
 */
@code("industry")
class FamilyCategory extends BaseCodeBean

/**
 * 户口类别
 * 参见国家标准GA 324.1-2001 见表C.3
 */
@code("industry")
class HouseholdType extends BaseCodeBean

/**
 * 护照类别
 * 参见GA 59.7 涉外信息管理代码 第 7 部分:护照证件种类代码
 */
@code("industry")
class PassportType extends BaseCodeBean

/**
 * 中国签证类别
 * GA/T 704.8-2007
 */
@code("industry")
class VisaType extends BaseCodeBean

