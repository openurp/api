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

package org.openurp.edu.course.model

import org.beangle.commons.lang.reflect.{BeanInfo, BeanInfos}
import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultCache("openurp.edu.course", "read-write")

    bind[Syllabus] declare { e =>
      e.hours is depends("syllabus")
      e.sections is depends("syllabus")
      e.texts is depends("syllabus")
      e.objectives is depends("syllabus")
      e.outcomes is depends("syllabus")
      e.topics is depends("syllabus")
      e.assessments is depends("syllabus")
      e.cases is depends("syllabus")
      e.experiments is depends("syllabus")
      e.designs is depends("syllabus")
    }

    bind[SyllabusTopic] declare { e =>
      e.elements is depends("topic")
      e.hours is depends("topic")
    }

    bind[SyllabusTopicHour]
    bind[SyllabusTopicElement]

    bind[SyllabusObjective]
    bind[SyllabusOutcome]
    bind[SyllabusCreditHour]
    bind[SyllabusSection]

    bind[SyllabusAssessment] declare { e =>
      e.description is length(4000)
      e.scoreTable is length(4000)
    }
    bind[SyllabusText] declare { e =>
      e.children is depends("parent")
    }

    bind[SyllabusCase] declare { e =>
      e.name is length(100)
    }

    bind[SyllabusExperiment] declare { e =>
      e.name is length(100)
    }

    bind[SyllabusDoc] declare { e =>
      e.docPath is length(200)
      index("", false, e.course)
    }

    bind[SyllabusMethodDesign] declare { e =>
      e.contents is length(2000)
    }

    bind[CourseTask] declare { e =>
      index("", true, e.semester, e.course, e.department)
    }
  }
}
