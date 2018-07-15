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
package org.openurp.code.edu.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean
/**
 * 学位
 * 参见国家推荐标准 GB/T 6864
 * @author chaostone
 * @since 2005-9-7
 */
@code("nation")
class Degree extends CodeBean {
  var level: DegreeLevel = _
}

/**
 * 学位层次
 * 参见国家推荐标准 GB/T 6864
 * @author chaostone
 * @since 2015-2-5
 */
@code("nation")
class DegreeLevel extends CodeBean

/**
 * 学科门类
 * 参见国家推荐标准GB/T 13745-2009
 * @see http://web.heuet.edu.cn/rsc/zhicheng2007/erjixuekemulu.doc
 */
@code("nation")
class DisciplineCategory extends CodeBean

/**
 * 学历(文化程度)
 * 参见国家推荐标准 GB/T 4658-2006
 * @see http://www.csres.com/detail/176312.html
 */
@code("nation")
class EducationDegree extends CodeBean {
  var level: AcademicLevel = _
  var result: EducationResult = _
}

/**
 * 学历层次
 * 参见国家推荐标准 GB/T 4658-2006
 * @see http://www.csres.com/detail/176312.html
 */
@code("nation")
class AcademicLevel extends CodeBean

/**
 * 教育培训结果
 * 参见国家推荐标准GB/T 14946.1—2009 附录A.27
 */
@code("nation")
class EducationResult extends CodeBean

/**
 * 语种
 * 参见国家推荐标准 GB/T 4880.2-2000
 * @see http://www.gfjl.org/thread-78200-1-1.html
 * @see http://zh.wikipedia.org/zh-cn/ISO_639-1%E4%BB%A3%E7%A0%81%E8%A1%A8
 * @see http://zh.wikipedia.org/wiki/ISO_639-2
 */
@code("nation")
class Language extends CodeBean

/**
 * 语种熟练程度
 * 参见国家推荐标准 GB/T 6865
 */
@code("nation")
class LanguageAbility extends CodeBean

/**
 * 学习形式
 * 参见国家推荐标准GB/T 14946.1—2009 附录A.3
 * @author chaostone
 */
@code("nation")
class StudyType extends CodeBean
