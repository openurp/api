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

package org.openurp.edu.program.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[Program] declare { e =>
      e.grade is length(10)
      e.name is length(100)
      e.termCampuses is depends("program")
      e.labels is depends("program")
      e.remark is length(500)
    }

    bind[ProgramCourseLabel]
    bind[TermCampus]

    bind[AbstractCourseGroup].declare { e =>
      e.indexno is length(20)
      e.remark is length(200)
    }

    //major plan
    bind[MajorCourseGroup].declare { e =>
      e.plan is target[MajorPlan]
      e.parent is target[MajorCourseGroup]
      e.children is depends(classOf[MajorCourseGroup], "parent")
      e.planCourses is depends(classOf[MajorPlanCourse], "group")
      e.termCredits is length(40)
      e.givenName is length(100)
      index("", false, e.plan)
      index("", false, e.parent)
    }

    bind[MajorPlan].declare { e =>
      e.groups is depends(classOf[MajorCourseGroup], "plan")
      index("", false, e.program)
    }

    bind[MajorPlanCourse].declare { e =>
      e.group is target[MajorCourseGroup]
      e.termText is length(20)
      e.remark is length(200)
      index("", true, e.group, e.course)
      index("", false, e.group)
    }

    // execution plan
    bind[ExecutiveCourseGroup].declare { e =>
      e.plan is target[ExecutivePlan]
      e.parent is target[ExecutiveCourseGroup]
      e.children is depends(classOf[ExecutiveCourseGroup], "parent")
      e.planCourses is depends(classOf[ExecutivePlanCourse], "group")
      e.termCredits is length(40)
      e.givenName is length(100)
      index("", false, e.plan)
      index("", false, e.parent)
    }

    bind[ExecutivePlan].declare { e =>
      e.groups is depends(classOf[ExecutiveCourseGroup], "plan")
      index("", false, e.program)
    }

    bind[ExecutivePlanCourse].declare { e =>
      e.group is target[ExecutiveCourseGroup]
      e.termText is length(20)
      e.remark is length(200)
      index("", true, e.group, e.course)
      index("", false, e.group)
    }

    //share plan
    bind[ShareCourseGroup].declare { e =>
      e.plan is target[SharePlan]
      e.parent is target[ShareCourseGroup]
      e.children is depends(classOf[ShareCourseGroup], "parent")
      e.planCourses is depends(classOf[SharePlanCourse], "group")
      index("", false, e.parent)
      index("", false, e.plan)
    }

    bind[SharePlan].declare { e =>
      e.groups is depends(classOf[ShareCourseGroup], "plan")
      e.remark is length(200)
    }

    bind[SharePlanCourse].declare { e =>
      e.group is target[ShareCourseGroup]
      index("", true, e.group, e.course)
      index("", false, e.group)
    }

    // std plan
    bind[StdCourseGroup].declare { e =>
      e.plan is target[StdPlan]
      e.parent is target[StdCourseGroup]
      e.children is depends(classOf[StdCourseGroup], "parent")
      e.planCourses is depends(classOf[StdPlanCourse], "group")
      index("", false, e.parent)
      index("", false, e.plan)
    }

    bind[StdPlan].declare { e =>
      e.groups is depends(classOf[StdCourseGroup], "plan")
      index("", true, e.std)
    }

    bind[StdPlanCourse].declare { e =>
      e.group is target[StdCourseGroup]
      index("", true, e.group, e.course)
      index("", false, e.group)
    }

    // alternative course
    bind[MajorAlternativeCourse].declare { e =>
      e.fromGrade & e.toGrade are length(10)
      index("", false, e.project)
    }

    bind[StdAlternativeCourse].declare { e =>
      index("", false, e.std)
    }

    bind[ProgramDocTemplate] declare { e =>
      e.metas is depends("template")
    }

    bind[ProgramDocMeta]

    bind[ProgramDoc] declare { e =>
      e.objectives is depends("doc")
      e.sections is depends("doc")
      e.outcomes is depends("doc")
      e.texts is depends("doc")
      e.tables is depends("doc")
      e.courseOutcomes is depends("doc")
    }

    bind[ProgramDocSection] declare { e =>
      e.contents is lob
      e.children is depends("parent")
    }

    bind[ExemptCourse]

    bind[StdExemptCourse] declare { e =>
      index("", false, e.std)
    }

    bind[ProgramObjective] declare { e =>
      e.contents is length(100)
      e.outcomes is length(40)
    }

    bind[ProgramOutline]

    bind[ProgramText] declare { e =>
      e.contents is length(4000)
    }

    bind[ProgramTable] declare { e =>
      e.contents is length(10000)
    }

    bind[ProgramOutcome] declare { e =>
      e.contents is length(4000)
    }

    bind[ProgramCourseOutcome] declare { e =>
      e.outcomes is length(50)
    }
  }
}
