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
package org.openurp.edu.course.model

import org.beangle.data.orm.MappingModule
import org.openurp.edu.course.code.model.ClazzTag

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("date")

    //code
    bind[ClazzTag].generator("auto_increment")

    //course
    bind[CourseTaker].declare { e =>
      e.remark is length(100)
      index("idx_course_taker", true, e.std, e.course, e.semester)
      index("idx_course_taker_clazz", false, e.clazz)
    }

    bind[Clazz].declare { e =>
      e.crn is length(32)
      e.teachers is ordered
      e.name is length(500)
      e.teachers is ordered
      e.enrollment.grade is length(20)
      e.exam.beginAt is column("exam_begin_at")
      e.exam.endAt is column("exam_end_at")
      e.enrollment.courseTakers & e.enrollment.restrictions &
        e.schedule.sessions are depends("clazz")

      index("idx_clazz", true, e.project, e.semester, e.crn)
      index("idx_clazz_depart", false, e.project, e.teachDepart)
    }

    bind[Restriction].declare { e =>
      e.items is depends("group")
      e.children is depends("parent")
      index("idx_restriction_clazz", false, e.clazz)
    }

    bind[RestrictionItem].declare { e =>
      index("idx_restriction_item_r", false, e.restriction)
    }

    bind[Lesson] declare { e =>
      index("idx_lesson_clazz", false, e.clazz)
    }

    //schedule
    bind[Session].declare { e =>
      e.remark is length(200)
      index("idx_session_clazz", false, e.clazz)
    }

    bind[ClazzGroup].declare { e =>
      e.clazzes is one2many("group")
    }

    bind[Material] declare { e =>
      e.clazz is notnull
      e.references is length(500)
      e.extra is length(200)
      e.reason is length(300)
      e.remark is length(200)

      index("idx_material_clazz", true, e.clazz)
    }

  }

}
