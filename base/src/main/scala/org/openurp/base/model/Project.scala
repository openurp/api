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

package org.openurp.base.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.pojo.{Coded, Named, TemporalOn, Updated}
import org.beangle.data.model.{IntId, LongId}
import org.openurp.base.edu.code.EducationType
import org.openurp.base.model.{Campus, Department, School}
import org.openurp.base.std.code.{StdLabel, StdType}
import org.openurp.code.edu.model.{EduCategory, EducationLevel}

import scala.collection.mutable

/**
 * 教学项目
 * </p>
 * 教学项目是指本科教学、研究生教学、辅修教学等教学项目。项目本身主要用于管理学校具备独立运作、具备较强隔离性的教学项目。
 * 他是按照EAMS核心业务范围建立的顶级概念。
 * 项目可以简要描述为：在1）固定的部门和授课团队等师资下，培养2）固定学历层次的学生，培养学生可以按照分类标签进行划分；
 * 具体培养过程中可以使用3）固定的校区和稳定的教学校历，这种过程和资源的总称为项目。
 *
 * @author chaostone
 * @since 3.0.0
 */
class Project extends IntId with Coded with TemporalOn with Updated with Named {
  /** 适用学校 */
  var school: School = _
  /** 校区列表 */
  var campuses: mutable.Buffer[Campus] = new mutable.ListBuffer[Campus]
  /** 部门列表 */
  var departments: mutable.Buffer[Department] = new mutable.ListBuffer[Department]
  /** 学历层次列表 */
  var levels: mutable.Buffer[EducationLevel] = new mutable.ListBuffer[EducationLevel]

  /** 培养类型列表 */
  var eduTypes: mutable.Buffer[EducationType] = new mutable.ListBuffer[EducationType]
  /** 学生分类列表 */
  var stdLabels: mutable.Buffer[StdLabel] = new mutable.ListBuffer[StdLabel]
  /** 学生类别 */
  var stdTypes: mutable.Buffer[StdType] = new mutable.ListBuffer[StdType]
  /** 使用校历 */
  var calendar: Calendar = _
  /** 描述 */
  var description: String = _
  /** 是否辅修 */
  var minor: Boolean = _
  /** 教育类别 */
  var category: EduCategory = _

}

/**
 * 项目基础代码配置
 * 表示项目使用了基础代码集合中的哪些基础代码
 *
 * @author chaostone
 */
class ProjectCode extends LongId {
  /** 项目 */
  var project: Project = _
  /** 代码元 */
  var className: String = _
  /** 代码IDs */
  var codeIds: String = _
}

/** 基于项目的
 */
trait ProjectBased {

  var project: Project = _
}

/**
 * 基于培养层次的实体接口
 * </p>
 * 基于项目和培养层次的实体接口，标准化了培养层次的属性名称。
 *
 * @see Squad
 * @see Student
 * @author chaostone
 */

trait EduLevelBased extends ProjectBased {
  /** 培养层次 */
  var level: EducationLevel = _
  /** 培养类型 */
  var eduType: EducationType = _
}

object ProjectPropertyType {
  val Integer = "integer"
  val Float = "float"
  val String = "string"
  val Boolean = "boolean"
  val Json = "json"
  val types = Set(Integer, Float, String, Boolean, Json)
}

class ProjectProperty extends LongId with ProjectBased {
  var name: String = _
  var description: String = _
  var typeName: String = _
  var value: String = _
}
