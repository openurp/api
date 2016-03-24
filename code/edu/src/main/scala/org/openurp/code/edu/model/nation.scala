/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.code.edu.model

import org.beangle.data.model.annotation.code
import org.openurp.code.BaseCodeBean
/**
 * 学位
 * 参见国家推荐标准 GB/T 6864
 * @author chaostone
 * @since 2005-9-7
 */
@code("nation")
class Degree extends BaseCodeBean {
  var level: DegreeLevel = _
}

/**
 * 学位层次
 * 参见国家推荐标准 GB/T 6864
 * @author chaostone
 * @since 2015-2-5
 */
@code("nation")
class DegreeLevel extends BaseCodeBean

/**
 * 学科门类
 * 参见国家推荐标准GB/T 13745-2009
 * @see http://web.heuet.edu.cn/rsc/zhicheng2007/erjixuekemulu.doc
 */
@code("nation")
class DisciplineCategory extends BaseCodeBean

/**
 * 学历(文化程度)
 * 参见国家推荐标准 GB/T 4658-2006
 * @see http://www.csres.com/detail/176312.html
 */
@code("nation")
class EducationDegree extends BaseCodeBean {
  var level: EducationLevel = _
  var result: EducationResult = _
}

/**
 * 学历层次
 * 参见国家推荐标准 GB/T 4658-2006
 * @see http://www.csres.com/detail/176312.html
 */
@code("nation")
class EducationLevel extends BaseCodeBean

/**
 * 教育培训结果
 * 参见国家推荐标准GB/T 14946.1—2009 附录A.27
 */
@code("nation")
class EducationResult extends BaseCodeBean

/**
 * 语种
 * 参见国家推荐标准 GB/T 4880.2-2000
 * @see http://www.gfjl.org/thread-78200-1-1.html
 * @see http://zh.wikipedia.org/zh-cn/ISO_639-1%E4%BB%A3%E7%A0%81%E8%A1%A8
 * @see http://zh.wikipedia.org/wiki/ISO_639-2
 */
@code("nation")
class Language extends BaseCodeBean

/**
 * 语种熟练程度
 * 参见国家推荐标准 GB/T 6865
 */
@code("nation")
class LanguageAbility extends BaseCodeBean


/**
 * 学习形式
 * 参见国家推荐标准GB/T 14946.1—2009 附录A.3
 * @author chaostone
 */
@code("nation")
class StudyType extends BaseCodeBean
