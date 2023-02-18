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

package org.openurp.edu.exam.model

import org.beangle.data.orm.MappingModule
import org.openurp.edu.exam.config.ExamAllocSetting

class DefaultMapping extends MappingModule {

  def binding(): Unit = {

    bind[ExamActivity].declare { e =>
      e.examTakers is depends("activity")
      e.remark is length(100)
      index("", false, e.clazz, e.examType)
      index("", false, e.semester)
      index("", false, e.task)
    }

    bind[ExamRoom].declare { e =>
      e.invigilations is depends("examRoom")
      e.examTakers is depends("examRoom")
      e.activities is many2many("rooms")
    }

    bind[ExamTask].declare { e =>
      e.activities is depends("task")
      index("", true, e.project, e.semester, e.code)
      index("", false, e.semester)
      index("", false, e.group)
    }

    bind[ExamGroup].declare { e =>
      e.turns is depends("group")
      e.tasks is one2many("group")
    }

    bind[ExamTurn] declare { e =>
      index("", false, e.group)
    }

    bind[ExamTaker] declare { e =>
      index("", true, e.std, e.clazz, e.examType)
      index("", false, e.clazz)
      index("", false, e.examRoom)
    }

    bind[Invigilation] declare { e =>
      index("", false, e.examRoom)
    }

    bind[InvigilationClazzQuota] declare { e =>
      index("", true, e.clazz, e.teacher)
    }

    bind[InvigilationQuota].declare { e =>
      e.details is depends("quota")
      e.excludes is eleColumn("exclude_on")
      index("", true, e.invigilator, e.semester)
    }

    bind[InvigilationQuotaDetail] declare { e =>
      index("", false, e.quota)
    }

    bind[ExamRoomGroup]

    bind[FinalMakeupCourse].declare { e =>
      e.takers is depends("makeupCourse")
    }

    bind[FinalMakeupTaker] declare { e =>
      index("", false, e.std)
      index("", false, e.makeupCourse)
    }

    bind[ExamNotice] declare { e =>
      e.studentNotice is length(1000)
      e.managerNotice is length(1000)
    }
    cache().add(classOf[ExamNotice])
  }
}
