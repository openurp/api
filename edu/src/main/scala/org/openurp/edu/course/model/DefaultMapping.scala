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

    bind[SyllabusTopicHour]

    bind[SyllabusTopicElement] declare { e =>
      e.contents is length(3000)
    }

    bind[SyllabusObjective] declare { e =>
      e.contents is length(1000)
    }

    bind[SyllabusOutcome] declare { e =>
      e.title is length(150)
      e.contents is length(1000)
    }

    bind[SyllabusCreditHour]

    bind[SyllabusAssessment] declare { e =>
      e.description is length(4000)
      e.scoreTable is length(4000)
    }
    bind[SyllabusText] declare { e =>
      e.contents is length(4000)
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
      e.contents is length(4000)
    }

    bind[CourseTask] declare { e =>
      index("", true, e.semester, e.course, e.department)
    }

    bind[TeachingPlan] declare { e =>
      e.lessons is depends("plan")
      e.sections is depends("plan")
      e.opinions is length(300)
      index("", true, e.clazz)
    }

    bind[Lesson] declare { e =>
      e.contents is length(2000)
      e.homework is length(400)
    }

    bind[TeachingPlanSection]

    bind[ExamAnalysis].declare { e =>
      e.contents is length(3500)
      index("", true, e.clazz)
    }
  }
}
