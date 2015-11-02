/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.program.plan.model

import org.beangle.data.model.{ LongId, Updated }
import org.openurp.base.model.Department
import org.openurp.code.edu.model.EducationLevel
import org.openurp.edu.base.code.model.StdType
import org.openurp.edu.base.model.{ Course, Direction, Major, Project, Student }
import org.openurp.edu.base.code.model.Education
import org.beangle.commons.collection.Collections

/**
 * 课程替代关系.
 *
 * @author new
 */
trait CourseSubstitution extends LongId with Updated {

  /**原课程*/
  var olds  = Collections.newSet[Course]

  /**新课程*/
  var news = Collections.newSet[Course]
}

/**
 * 专业替代课程.
 */
class MajorCourseSubstitution extends CourseSubstitution {

  /**
   * 项目
   */
  var project: Project = _

  /**
   * 院系
   */
  var department: Department = _

  /**
   * 获取培养层次
   */
  var education: Education = _
  /**
   * 起始年级.
   */
  var fromGrade: String = _

  /**截至年级*/
  var toGrade: String = _
  /**
   * 适用专业.
   */
  var major: Major = _

  /**
   * 适用方向.
   */
  var direction: Direction = _

  /**
   * 学生类别
   */
  var stdType: StdType = _
}

/**
 * 学生替代课程.
 */
class StdCourseSubstitution extends CourseSubstitution {

  /**
   * 获取学生
   */
  var std: Student = _
}
