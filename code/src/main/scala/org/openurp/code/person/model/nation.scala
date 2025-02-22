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

package org.openurp.code.person.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean

/** 港澳台侨
 * 参见国家标准 GB_T 14946.1-2009 A2
 */
@code("nation")
class CompatriotType extends CodeBean

/** 人员关系
 * 参见国家推荐标准GB/T 4761-2008
 *
 * @see http://www.sac.gov.cn/SACSearch/search?channelid=160591&templet=gjcxjg_detail.jsp&searchword=STANDARD_CODE=%27GB/T%204761-2008%27&XZ=T
 */
@code("nation")
class FamilyRelationship extends CodeBean

/** 性别
 * 参见国家推荐标准 GB/T 2261.1-2003
 *
 * @see http://www.gfjl.org/thread-70877-1-1.html
 * @see http://en.wikipedia.org/wiki/ISO/IEC_5218
 */
@code("nation")
class Gender extends CodeBean

/** 健康状况
 * 参见国家标准GB/T 2261.3
 */
@code("nation")
class HealthStatus extends CodeBean

/** 证件类型
 * 参见国家标准GB/T 14946.1-2009 附录A.84
 */
@code("nation")
class IdType extends CodeBean

/** 从业状况
 * 参见国家标准GB/T 2261.4-2003
 */
@code("nation")
class JobStatus extends CodeBean

/** 婚姻状况
 * 参见国家标准GB 2261.2-2003
 */
@code("nation")
class MaritalStatus extends CodeBean

/** 民族
 * 参见国家标准 GB 3304-91
 *
 * @see http://www.gfjl.org/thread-74491-1-1.html
 */
@code("nation")
class Nation extends CodeBean {
  var alphaCode: String = _
}

/** 政治面貌
 * 参见国家标准 GB 4762-84
 *
 * @see http://www.gfjl.org/thread-79332-1-1.html
 */
@code("nation")
class PoliticalStatus extends CodeBean {
  /** 简称 */
  var shortName: Option[String] = None
}

/** 宗教信仰
 * 参见国家标准GA 214.12
 */
@code("nation")
class Religion extends CodeBean

/** 语种
 * 参见国家推荐标准 GB/T 4880.2-2000
 *
 * @see http://www.gfjl.org/thread-78200-1-1.html
 * @see http://zh.wikipedia.org/zh-cn/ISO_639-1%E4%BB%A3%E7%A0%81%E8%A1%A8
 * @see http://zh.wikipedia.org/wiki/ISO_639-2
 */
@code("nation")
class Language extends CodeBean
