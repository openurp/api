/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
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
package org.openurp.code.edu.model

import org.beangle.commons.model.annotation.code
import org.openurp.code.BaseCodeBean

/**
 * 录取类别
 * 参见教育部标准JY/T 1001 4.2.28.3
 */
@code("industry")
class AdmissionType extends BaseCodeBean

/**
 * 活动类型
 *  参考教育部标准JY/T 1001 4.3.4（教室占用情况）
 */
@code("industry")
class ActivityType extends BaseCodeBean

/**
 * 教室类型
 * 参见教育部标准JY/T 1001 4.3.3
 */
@code("industry")
class ClassroomType extends BaseCodeBean
/**
 * 学科目录
 *
 * 一般有：本科学科目录、研究生学术性学位目录、研究生专业性学位目录
 */
@code("industry")
class DisciplineCatalog extends BaseCodeBean
/**
 * 学科
 * （来自本专科科学科目录、研究生学术性学位目录、研究生专业性学位目录的学科）<br>
 * 一般认为4位代码的是一级学科，2位代码的是二级学科，但实际上可能没有一级学科、二级学科这种叫法
 *
 * @author chaostone
 */
@code("industry")
class Discipline extends BaseCodeBean {
  var category: DisciplineCategory = _
  var catalog: DisciplineCatalog = _
}

/**
 * 入学方式
 * 参见教育部推荐标准JY/T 1001 4.2.32
 */
@code("industry")
class EnrollMode extends BaseCodeBean

/**
 * 培养方式
 * 参见教育部标准JY/T 1001 4.2.30
 */
@code("industry")
class EducationMode extends BaseCodeBean

/**
 * 留学生HSK等级
 * 参见教育部标准JY/T 1001 4.5.4
 */
@code("industry")
class HskLevel extends BaseCodeBean

/**
 * 科研机构
 * @see http://www.stats.edu.cn/rjgx/dm/%E6%99%AE%E9%80%9A%E9%AB%98%E6%A0%A1%E4%BB%A3%E7%A0%81.htm
 */
@code("industry")
class Institution extends BaseCodeBean

/**
 * 学生处分类型
 * 参见教育部标准JY/T 1001 4.2.3
 */
@code("industry")
class StudentPunishmentType extends BaseCodeBean

/**
 * 学籍异动类别
 * 参见教育部标准JY/T 1001 4.2.39
 */
@code("industry")
class StudentAlterType extends BaseCodeBean

/**
 * 学籍异动原因
 * 参见教育部标准JY/T 1001 4.2.40
 */
@code("industry")
class StudentAlterReason extends BaseCodeBean

/**
 * 学生当前状态
 * 参见教育部标准JY/T 1001 4.2.42
 */
@code("industry")
class StudentStatus extends BaseCodeBean

/**
 * 高考科目
 * 参见教育部标准JY/T 1001 4.2.8
 */
@code("industry")
class UeeSubjectType extends BaseCodeBean
