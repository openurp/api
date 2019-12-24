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
package org.openurp.edu.exam.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[ExamActivity].declare { e =>
      e.examTakers is depends("activity")
      e.remark is length(100)
    }
    bind[ExamRoom].declare { e =>
      e.invigilations is depends("examRoom")
      e.examTakers is depends("examRoom")
    }
    bind[ExamTask].declare { e =>
      e.examClazzes is depends("task")
    }
    bind[ExamClazz]

    bind[ExamGroup].declare { e =>
      e.tasks is one2many("group")
      e.turns is depends("group")
    }
    bind[ExamTurn]

    bind[ExamTaker]

    bind[Invigilation]
    bind[InvigilationClazzQuota]

    bind[InvigilationQuota].declare { e =>
      e.details is depends("invigilationQuota")
      e.excludes is eleColumn("exclude_on")
    }
    bind[InvigilationQuotaDetail]

    bind[RoomGroup]

    bind[RoomAllocSetting]

    bind[FinalMakeupCourse].declare { e =>
      e.takers is depends("makeupCourse")
    }
    bind[FinalMakeupTaker]
  }

}
