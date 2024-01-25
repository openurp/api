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

package org.openurp.edu.program.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.pojo.{Hierarchical, Named, TemporalOn, Updated}
import org.beangle.data.model.{IntId, LongId}
import org.openurp.base.model.{Department, EduLevelBased, Project}
import org.openurp.code.edu.model.EducationLevel
import org.openurp.code.std.model.StdType

import java.util.Locale
import scala.collection.mutable

/** 培养方案文档
 * 该文档与培养方案一对一
 */
class ProgramDoc extends LongId with Updated {
  /** 语言 */
  var docLocale: Locale = _
  /** 方案 */
  var program: Program = _

  /** 章节列表 */
  var sections: mutable.Buffer[ProgramDocSection] = Collections.newBuffer[ProgramDocSection]
}

/** 培养方案章节
 * */
class ProgramDocSection extends LongId with Named with Hierarchical[ProgramDocSection] {

  /** 内容 */
  var contents: String = _

  /** 文档 */
  var doc: ProgramDoc = _
}

/** 培养方案文档模板
 * 限定在某个层次的,某个部门的文档模板，部门可选
 */
class ProgramDocTemplate extends IntId, Named, EduLevelBased, TemporalOn, Updated {
  /** 部门 */
  var department: Option[Department] = None
  /** 语言 */
  var docLocale: Locale = _
  /** 针对学生类别 */
  var types: mutable.Set[StdType] = Collections.newSet[StdType]
  /** 章节元数据 */
  var metas: mutable.Buffer[ProgramDocMeta] = Collections.newBuffer[ProgramDocMeta]
}

/** 方案模板章节定义
 * */
class ProgramDocMeta extends IntId with Named {

  /** 章节编号 */
  var indexno: String = _

  /** 模板 */
  var template: ProgramDocTemplate = _

  /** 最大长度 */
  var maxlength: Int = _

}
