/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.exam.model

import scala.reflect.runtime.universe
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.annotation.code
import org.beangle.data.orm.MappingModule
import org.openurp.edu.course.code.model.ClazzTag

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[ExamActivity].on(e => declare(
      e.examStudents is depends("activity"),
      e.remark is length(100)))

    bind[ExamRoom].on(e => declare(
      e.invigilations is depends("examRoom"),
      e.examStudents is depends("examRoom")))

    bind[ExamTask].on(e => declare(
      e.examClazzes is depends("task")))

    bind[ExamClazz]

    bind[ExamGroup].on(e => declare(
      e.tasks is one2many("group"),
      e.turns is depends("group")))

    bind[ExamTurn]

    bind[ExamStudent]

    bind[Invigilation]
    bind[InvigilationClazzQuota]

    bind[InvigilationQuota].on(e => declare(
      e.details is depends("invigilationQuota"),
      e.excludes is eleColumn("exclude_on")))

    bind[InvigilationQuotaDetail]

    bind[RoomGroup]

    bind[RoomAllocSetting]
  }

}
