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
package org.openurp.hr.base.model

import org.beangle.data.model.{ LongId, TemporalOn }
import org.openurp.base.model.Department
import org.openurp.code.job.model.{ PostGrade, PostType, ProfessionalTitle }

/**
 * 岗位信息
 */
class PostInfo extends LongId with TemporalOn {

  /**教工*/
  var staff: Staff = _

  /**岗位类别*/
  var postType: PostType = _

  /**岗位等级*/
  var postGrade: PostGrade = _

  /**是否兼职*/
  var parttime: Boolean = _
  
  /**辅导员类型*/
  

}
