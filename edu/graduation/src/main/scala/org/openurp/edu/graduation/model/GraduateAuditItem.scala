/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright (c) 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.graduation.model

import org.beangle.data.model.LongId

/**
 * 毕业审核明细
 */
class GraduateAuditItem extends LongId {

  /** 项目名称 */
  var name: String = _

  /** 是否通过 */
  var passed: Boolean = _

  /** 具体状态信息 */
  var comments: Option[String] = None

  /** 毕业审核结果 */
  var result: AuditResult = _

  def this(name: String, auditResult: AuditResult) {
    this()
    this.name = name
    this.result = auditResult
  }
}
