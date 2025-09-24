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

package org.openurp.edu.clazz.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    //course
    bind[CourseTaker].declare { e =>
      e.remark is length(100)
      index("", true, e.std, e.course, e.semester)
      index("", false, e.clazz)
      index("", false, e.semester)
    }

    bind[Clazz].declare { e =>
      e.crn is length(32)
      e.teachers is ordered
      e.clazzName is length(500)
      e.enrollment.grades is length(40)
      e.enrollment.stdCount is immutable
      e.enrollment.courseTakers & e.enrollment.restrictions & e.enrollment.subclazzes &
        e.schedule.activities are depends("clazz")
      index("", true, e.project, e.semester, e.crn)
      index("", false, e.project, e.semester, e.teachDepart)
    }

    bind[ClazzFinalExam].declare { e =>
      e.clazz is unique
    }

    bind[ClazzRestriction].declare { e =>
      e.items is depends("restriction")
      e.children is depends("parent")
      index("", false, e.clazz)
    }

    bind[ClazzRestrictionItem].declare { e =>
      e.contents is length(1000)
      index("", false, e.restriction)
    }

    //schedule
    bind[ClazzActivity].declare { e =>
      e.remark is length(200)
      e.teachers is joinColumn("activity_id")
      e.rooms is joinColumn("activity_id")
      index("", false, e.clazz)
    }

    bind[ClazzGroup].declare { e =>
      e.clazzes is one2many("group")
    }

    bind[StdCourseAbility] declare { e =>
      index("", false, e.std)
    }

    bind[Subclazz] declare { e =>
      e.name is length(100)
    }

    bind[ClazzNotice] declare { e =>
      e.title is length(300)
      e.contents is length(1500)
      e.files is depends("notice")
      index("", false, e.clazz)
    }

    bind[ClazzNoticeFile] declare { e =>
      e.filePath is length(200)
      e.mediaType is length(100)
    }

    bind[ClazzDoc].declare { e =>
      e.name is length(300)
      e.filePath is length(400)
      e.url is length(400)
      index("", false, e.clazz)
    }

    bind[ClazzBulletin] declare { e =>
      e.contents is length(1000)
      e.contactChannel is length(150)
      e.contactQrcodePath is length(300)
      index("", true, e.clazz)
    }

    bind[StdCreditStat]
    bind[ScheduleSuggest] declare { e =>
      e.activities is depends("suggest")
      index("", true, e.clazz)
    }
    bind[ScheduleSuggestActivity]

    bind[MiniClazz] declare { e =>
      e.activities is depends("miniClazz")
    }
    bind[MiniClazzActivity] declare { e =>
      e.places is length(200)
    }
  }
}
