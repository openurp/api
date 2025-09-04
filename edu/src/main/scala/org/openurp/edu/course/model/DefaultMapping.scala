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
      e.methods is length(500)
      e.hours is depends("syllabus")
      e.texts is depends("syllabus")
      e.objectives is depends("syllabus")
      e.outcomes is depends("syllabus")
      e.topics is depends("syllabus")
      e.assessments is depends("syllabus")
      e.cases is depends("syllabus")
      e.experiments is depends("syllabus")
      e.designs is depends("syllabus")
      e.bibliography is length(2000)
      e.materials is length(2000)
      e.opinions is length(300)
      index("", true, e.semester, e.course, e.writer, e.docLocale)
    }

    bind[SyllabusTopic] declare { e =>
      e.contents is length(4000)
      e.elements is depends("topic")
      e.hours is depends("topic")
    }

    bind[SyllabusTopicHour] declare { e =>
      index("", true, e.topic, e.nature)
    }

    bind[SyllabusTopicElement] declare { e =>
      e.contents is length(3000)
    }

    bind[SyllabusObjective] declare { e =>
      e.contents is length(1000)
      index("", true, e.syllabus, e.code)
    }

    bind[SyllabusOutcome] declare { e =>
      e.title is length(150)
      e.contents is length(1000)
    }

    bind[SyllabusHour]

    bind[SyllabusAssessment] declare { e =>
      e.description is length(4000)
      e.scoreTable is length(10000)
    }
    bind[SyllabusText] declare { e =>
      e.contents is length(4000)
      e.children is depends("parent")
    }

    bind[SyllabusCase] declare { e =>
      e.name is length(100)
    }

    bind[SyllabusExperiment]

    bind[SyllabusDoc] declare { e =>
      e.docPath is length(200)
      index("", false, e.course)
    }

    bind[SyllabusMethodDesign] declare { e =>
      e.contents is length(4000)
    }

    bind[CourseTask] declare { e =>
      index("", true, e.semester, e.course, e.idx)
    }

    bind[ClazzPlan] declare { e =>
      e.lessons is depends("plan")
      e.hours is depends("plan")
      e.opinions is length(300)
      index("", true, e.clazz)
    }

    bind[ClazzSectionHour]

    bind[Lesson] declare { e =>
      e.contents is length(2000)
      e.homework is length(400)
    }

    bind[ClazzProgram] declare { e =>
      e.designs is depends("program")
    }

    bind[LessonDesign] declare { e =>
      e.texts is depends("design")
      e.subject is length(300)
      e.homework is length(2000)
      e.sections is depends("design")
    }

    bind[LessonDesignText] declare { e =>
      e.name is length(100)
      e.contents is length(4000)
    }

    bind[LessonDesignSection] declare { e =>
      e.title is length(400)
      e.summary is length(50000)
      e.details is length(50000)
    }

    bind[ExamAnalysis].declare { e =>
      e.contents is length(4000)
      index("", true, e.clazz)
    }

    bind[ClazzArchive].declare { e =>
      e.docPath is length(400)
      index("", true, e.clazz, e.doc)
    }
  }
}
