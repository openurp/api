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
package org.openurp.code.job.model

import org.beangle.commons.model.annotation.code
import org.openurp.code.BaseCodeBean
/**
 * 职务类别
 */
@code("nation")
class DutyType extends BaseCodeBean

/**
 * 行政职务级别
 */
@code("nation")
class DutyGrade extends BaseCodeBean {
  /**
   * 行政职务类别
   */
  var dutyType: DutyType = _
}

/**
 * 职称等级
 */
@code("nation")
class ProfessionalGrade extends BaseCodeBean

/**
 * 岗位类别
 */
@code("nation")
class PostType extends BaseCodeBean

/**
 * 岗位等级
 */
@code("nation")
class PostGrade extends BaseCodeBean

/**
 * 职称
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("nation")
class ProfessionalTitle extends BaseCodeBean {
  /**
   * 职称等级
   */
  var grade: ProfessionalGrade = _
}
