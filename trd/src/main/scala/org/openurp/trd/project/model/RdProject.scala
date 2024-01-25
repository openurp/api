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

package org.openurp.trd.project.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Named, Remark, Updated}
import org.openurp.base.model.{Department, User}
import org.openurp.code.trd.model.RdLevel
import org.openurp.trd.project.code.{RdProjectCategory, RdProjectStatus}

import java.time.YearMonth
import scala.collection.mutable

/** 研究项目
 * 包含课程建设项目、教改项目
 */
class RdProject extends LongId with Coded with Named with Remark with Updated {

  /** 是否课程建设项目 */
  var forCourse: Boolean = _

  /** 负责人 */
  var leaders: mutable.Buffer[User] = Collections.newBuffer[User]

  /** 参与人 */
  var members: mutable.Buffer[RdProjectMember] = Collections.newBuffer[RdProjectMember]

  /** 部门 */
  var department: Department = _

  /** 级别 */
  var level: RdLevel = _

  /** 类别 */
  var category: RdProjectCategory = _

  /** 资金 */
  var funds: Int = _

  /** 立项年月 */
  var beginOn: YearMonth = _

  /** 应结束年月 */
  var endOn: YearMonth = _

  /** 实际结项年月 */
  var finishedOn: Option[YearMonth] = None

  /** 状态 */
  var status: RdProjectStatus = _

  def leaderNames: String = {
    leaders.map(_.name).mkString(",")
  }

  def memberNames: String = {
    members.map(_.name).mkString(",")
  }

  def getMember(idx: Int): Option[RdProjectMember] = {
    members.find(_.idx == idx)
  }
}

/** 项目成员
 *
 */
class RdProjectMember extends LongId {
  /** 排名 */
  var idx: Int = _
  /** 姓名 */
  var name: String = _
  /** 参与人 */
  var user: Option[User] = None
  /** 项目 */
  var project: RdProject = _
}
