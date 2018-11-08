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

import scala.reflect.runtime.universe
import org.beangle.data.model.annotation.{ code, config }
import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[GraduateSession].on(e => declare(
      e.name is length(100)))

    bind[GraduateAuditItem].on(e => declare(
      e.name is length(100),
      e.comments is length(500)))

    bind[DegreeAuditItem].on(e => declare(
      e.name is length(100),
      e.comments is length(500)))

    bind[AuditResult].on(e => declare(
      e.graduate.items & e.degree.items are depends("result"),
      e.graduate.locked is column("graduate_locked"),
      e.graduate.passed is column("graduate_passed"),
      e.graduate.published is column("graduate_published"),
      e.graduate.comments is (column("graduate_comments"), length(500)),

      e.degree.locked is column("degree_locked"),
      e.degree.passed is column("degree_passed"),
      e.degree.published is column("degree_published"),
      e.degree.comments is (column("degree_comments"), length(500))))

    bind[LastMakeupClazz].on(e => declare(
      e.takers is depends("clazz")))

    bind[LastMakeupTaker]

  }

}
