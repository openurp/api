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
package org.openurp.hr.info.model

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.Degree
import org.openurp.hr.base.model.Staff
import java.sql.Date
import org.beangle.data.model.TemporalOn

/**
 * 教职工学位信息
 */
class DegreeInfo extends LongId with TemporalOn {

  /**
   * 教职工
   */
  var staff: Staff = _

  /**
   * 学位
   */
  var degree: Degree = _

  /**
   * 学位证书号
   */
  var certificateNo: String = _
  
  /**
   * 学位专业
   */
  var major: String = _

  /**
   * 授予单位
   */
  var conferralBy: String = _

}