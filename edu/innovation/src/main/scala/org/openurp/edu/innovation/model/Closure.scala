/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
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
package org.openurp.edu.innovation.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated

import scala.collection.mutable

/**
 * 项目结项
 */
class Closure extends LongId with Updated {
  var project: Project = _

  var applyExemptionReply: Boolean = _

  var exemptionReason: Option[String] = None

  var exemptionConfirmed: Option[Boolean] = None

  var applyRejectComment: Option[String] = None

  var replyScore: Option[Int] = None

  def this(project: Project) {
    this()
    this.project = project
  }
}
