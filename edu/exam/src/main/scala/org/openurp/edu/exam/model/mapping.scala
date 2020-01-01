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
      index("idx_exam_activity", true, e.clazz, e.examType)
      index("idx_exam_activity_semester", false, e.semester)
    }

    bind[ExamRoom].declare { e =>
      e.invigilations is depends("examRoom")
      e.examTakers is depends("examRoom")
    }

    bind[ExamTask].declare { e =>
      e.examClazzes is depends("task")
      index("idx_exam_task", true, e.project, e.semester, e.code)
      index("idx_exam_task_semester", false, e.semester)
      index("idx_exam_task_group", false, e.group)
    }

    bind[ExamClazz] declare { e =>
      index("idx_exam_clazz", true, e.clazz, e.examType)
      index("idx_exam_clazz_task", false, e.task)
    }

    bind[ExamGroup].declare { e =>
      e.turns is depends("group")
      e.tasks is one2many("group")
    }

    bind[ExamTurn] declare { e =>
      index("idx_exam_turn_group", false, e.group)
    }

    bind[ExamTaker] declare { e =>
      index("idx_exam_taker", true, e.std, e.clazz, e.examType)
      index("idx_exam_taker_clazz", false, e.clazz)
      index("idx_exam_taker_exam_room", false, e.examRoom)
    }

    bind[Invigilation] declare { e =>
      index("idx_invigilation_exam_room", false, e.examRoom)
    }

    bind[InvigilationClazzQuota] declare { e =>
      index("idx_invigilation_clazz_quota", true, e.clazz, e.teacher)
    }

    bind[InvigilationQuota].declare { e =>
      e.details is depends("invigilationQuota")
      e.excludes is eleColumn("exclude_on")
      index("idx_invigilation_quota", true, e.invigilator, e.semester)
    }

    bind[InvigilationQuotaDetail] declare { e =>
      index("idx_invigilation_quota_detail1", false, e.quota)
    }

    bind[RoomGroup]

    bind[RoomAllocSetting]

    bind[FinalMakeupCourse].declare { e =>
      e.takers is depends("makeupCourse")
    }

    bind[FinalMakeupTaker] declare { e =>
      index("idx_final_makeup_taker_std", false, e.std)
      index("idx_final_makeup_taker_course", false, e.makeupCourse)
    }
  }

}
