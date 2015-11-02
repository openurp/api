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
package org.openurp.edu.evaluation.model

import org.beangle.data.model.LongId
import org.openurp.base.model.Department
import scala.collection.mutable.HashSet
import java.sql.Date
import org.beangle.data.model.TemporalOn
import org.beangle.data.model.Updated
/**
 * 评教问卷
 */
class Questionnaire extends LongId with Updated with TemporalOn {
  /** 问卷标题 */
  var title: String = _
  /** 简单描述 */
  var description: String = _
  /** 相关联的问题 */
  var questions: HashSet[Question] = new collection.mutable.HashSet[Question]
  /** 创建部门 */
  var depart: Department = _
  /** 备注 */
  var remark: String = _
  /** 创建者 */
  var createBy: String = _
  /** 使用状态 */
  var state: Boolean = false
}
