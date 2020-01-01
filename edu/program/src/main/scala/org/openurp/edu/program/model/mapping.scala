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
package org.openurp.edu.program.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("date")

    bind[Program] declare { e =>
      e.grade is length(10)
      e.name is length(100)
      e.termCampuses is depends("program")
      e.remark is length(200)
    }

    bind[TermCampus] declare { e =>
      index("idx_term_campus_program", true, e.program)
    }

    bind[AbstractCourseGroup].declare { e =>
      e.indexno is length(20)
      e.remark is length(200)
    }

    bind[AbstractPlanCourse].declare { e =>
      e.remark is length(200)
    }
    //major plan
    bind[MajorCourseGroup].declare { e =>
      e.plan is target[MajorPlan]
      e.parent is target[MajorCourseGroup]
      e.children is depends(classOf[MajorCourseGroup], "parent")
      e.planCourses is depends(classOf[MajorPlanCourse], "group")
      e.termCredits is length(40)
      e.alias is length(100)
      index("idx_major_plan_group_plan", false, e.plan)
      index("idx_major_plan_course_parent", false, e.parent)
    }

    bind[MajorPlan].declare { e =>
      e.endOn is notnull
      e.groups is depends(classOf[MajorCourseGroup], "plan")
      e.remark is length(200)
      index("idx_major_plan", false, e.program)
    }

    bind[MajorPlanCourse].declare { e =>
      e.group is target[MajorCourseGroup]
      index("idx_major_plan_course", true, e.group, e.course)
      index("idx_major_plan_course_group", false, e.group)
    }

    // execution plan
    bind[ExecutionCourseGroup].declare { e =>
      e.plan is target[ExecutionPlan]
      e.parent is target[ExecutionCourseGroup]
      e.children is depends(classOf[ExecutionCourseGroup], "parent")
      e.planCourses is depends(classOf[ExecutionPlanCourse], "group")
      e.termCredits is length(40)
      e.alias is length(100)
      index("idx_exe_course_group_plan", false, e.plan)
      index("idx_exe_course_group_parent", false, e.parent)
    }

    bind[ExecutionPlan].declare { e =>
      e.endOn is notnull
      e.groups is depends(classOf[ExecutionCourseGroup], "plan")
      e.remark is length(200)
      index("idx_execution_plan", false, e.program)
    }

    bind[ExecutionPlanCourse].declare { e =>
      e.group is target[ExecutionCourseGroup]
      index("idx_exe_plan_course", true, e.group, e.course)
      index("idx_exe_plan_course_group", false, e.group)
    }

    //share plan
    bind[ShareCourseGroup].declare { e =>
      e.plan is target[SharePlan]
      e.parent is target[ShareCourseGroup]
      e.children is depends(classOf[ShareCourseGroup], "parent")
      e.planCourses is depends(classOf[SharePlanCourse], "group")
      index("idx_share_course_group_parent", false, e.parent)
      index("idx_share_course_group_plan", false, e.plan)
    }

    bind[SharePlan].declare { e =>
      e.endOn is notnull
      e.groups is depends(classOf[ShareCourseGroup], "plan")
      e.remark is length(200)
    }

    bind[SharePlanCourse].declare { e =>
      e.group is target[ShareCourseGroup]
      index("idx_share_plan_course", true, e.group, e.course)
      index("idx_share_plan_course_group", false, e.group)
    }

    // std plan
    bind[StdCourseGroup].declare { e =>
      e.plan is target[StdPlan]
      e.parent is target[StdCourseGroup]
      e.children is depends(classOf[StdCourseGroup], "parent")
      e.planCourses is depends(classOf[StdPlanCourse], "group")
      index("idx_std_course_group_parent", false, e.parent)
      index("idx_std_course_group_plan", false, e.plan)
    }

    bind[StdPlan].declare { e =>
      e.endOn is notnull
      e.groups is depends(classOf[StdCourseGroup], "plan")
      e.remark is length(200)
      index("idx_std_plan", true, e.std)
    }

    bind[StdPlanCourse].declare { e =>
      e.group is target[StdCourseGroup]
      index("idx_std_plan_course", true, e.group, e.course)
      index("idx_std_plan_course_group", false, e.group)
    }

    // alternative course
    bind[MajorAlternativeCourse].declare { e =>
      e.fromGrade & e.toGrade are length(10)
      index("idx_major_alt_course_project", false, e.project)
    }.table("major_alt_courses")

    bind[StdAlternativeCourse].declare { e =>
      index("idx_std_alt_course_std", false, e.std)
    }.table("std_alt_courses")
  }
}
