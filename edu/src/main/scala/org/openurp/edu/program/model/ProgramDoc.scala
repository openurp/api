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
import org.openurp.base.model.{Department, EduLevelBased}
import org.openurp.code.std.model.StdType

import java.time.Instant
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
  /** 培养目标 */
  var objectives = Collections.newBuffer[ProgramObjective]
  /** 毕业要求对培养目标的支撑 */
  var outcomes = Collections.newBuffer[ProgramOutcome]
  //text sections
  var texts = Collections.newBuffer[ProgramText]
  //text sections
  var tables = Collections.newBuffer[ProgramTable]
  /** 课程对毕业要求的支撑 */
  var courseOutcomes = Collections.newBuffer[ProgramCourseOutcome]
  /** 章节列表 */
  var sections: mutable.Buffer[ProgramDocSection] = Collections.newBuffer[ProgramDocSection]

  def this(program: Program, other: ProgramDoc) = {
    this()
    this.docLocale = other.docLocale
    this.program = program
    other.objectives foreach { o =>
      val po = new ProgramObjective(this, o.code, o.contents)
      po.outcomes = o.outcomes
      this.objectives.addOne(po)
    }
    other.outcomes foreach { o =>
      this.outcomes.addOne(new ProgramOutcome(this, o.idx, o.title, o.contents))
    }
    other.texts foreach { o =>
      this.texts.addOne(new ProgramText(this, o.name, o.title, o.contents))
    }
    other.tables foreach { o =>
      this.tables.addOne(new ProgramTable(this, o.name, o.caption, o.contents))
    }
    other.courseOutcomes foreach { o =>
      val pco = new ProgramCourseOutcome(this, o.idx, o.groupName, o.courseName, o.course, o.outcomes)
      this.courseOutcomes.addOne(pco)
    }
    other.sections foreach { s =>
      val section = new ProgramDocSection(this, s.indexno, s.name, s.contents)
      this.sections.addOne(section)
    }
    this.updatedAt = Instant.now
  }

  def getText(name: String): Option[ProgramText] = {
    texts.find(_.name == name)
  }

  def getTexts(prefix: String): mutable.Buffer[ProgramText] = {
    texts.filter(_.name.startsWith(prefix))
  }

  def getTable(name: String): Option[ProgramTable] = {
    tables.find(_.name == name)
  }

  def getObjective(code: String): Option[ProgramObjective] = {
    objectives.find(_.code == code)
  }

  def getOutcome(o: String): Option[ProgramOutcome] = {
    outcomes.find(_.title == o)
  }
}

/** 培养方案章节
 * */
class ProgramDocSection extends LongId with Named with Hierarchical[ProgramDocSection] {

  /** 内容 */
  var contents: String = _

  /** 文档 */
  var doc: ProgramDoc = _

  def this(doc: ProgramDoc, indexno: String, name: String, contents: String) = {
    this()
    this.doc = doc
    this.name = name
    this.contents = contents
    this.indexno = indexno
  }
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
