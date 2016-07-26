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
package org.openurp.edu.base.model

import org.beangle.data.model.{ LongId, Named, Remark, TemporalOn, Updated }
import org.beangle.data.model.annotation.code
import org.openurp.base.model.Department
import org.openurp.code.edu.model.{ Degree, StudyType }
import org.openurp.edu.base.{ EducationBased, States }
import org.openurp.edu.base.code.model.StdType

/**
 * 专业培养方案
 * @author chaostone
 *
 */
class Program extends LongId with Updated with Named with Cloneable with TemporalOn with EducationBased with Remark {

  /**
   * 年级
   */
  var grade: String = _

  /**
   * 部门
   */
  var department: Department = _

  /**
   * 学生类别
   */
  var stdType: StdType = _

  /**
   * 专业
   */
  var major: Major = _

  /**
   * 专业方向
   */
  var direction: Option[Direction] = None

  /**
   * 学制
   */
  var duration: Float = _

  /**
   * 学习形式
   */
  var studyType: StudyType = _

  /**
   * 毕业授予学位
   */
  var degree: Option[Degree] = None

  /**
   * 审核状态
   */
  var state: States.State = States.Draft
}