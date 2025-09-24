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

package org.openurp.lab.experiment.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Remark
import org.openurp.base.edu.model.{Course, Experiment, TeachingOffice}
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{Department, Semester}
import org.openurp.base.resource.model.Laboratory
import org.openurp.code.edu.model.{CourseNature, CourseRank}

import scala.collection.mutable

/** 实验任务
 */
@beta
class LabTask extends LongId, Remark {
  /** 课程 */
  var course: Course = _
  /** 理论学时* */
  var theoryHours: Int = _
  /** 实践学时 */
  var practiceHours: Int = _
  /** 课程性质 */
  var nature: CourseNature = _
  /** 学年学期 */
  var semester: Semester = _
  /** 开课院系 */
  var department: Department = _
  /** 教研室 */
  var office: Option[TeachingOffice] = None
  /** 负责人 */
  var director: Option[Teacher] = None
  /** 是否要求填写实验项目 */
  var required: Boolean = true
  /** 填写实验项目数 */
  var expCount: Int = _
  /** 实验人数 */
  var stdCount: Int = _
  /** 实验室列表 */
  var labs: mutable.Set[Laboratory] = Collections.newSet[Laboratory]
  /** 必选修 */
  var rank: CourseRank = _
  /** 班级数 */
  var clazzCount: Int = _
  /** 实验列表 */
  var experiments: mutable.Buffer[LabExperiment] = Collections.newBuffer[LabExperiment]
  /** 数据是否完整 */
  var validated: Boolean = false

  def this(course: Course, semester: Semester, department: Department) = {
    this()
    this.course = course
    this.semester = semester
    this.department = department
  }

  def remove(exp: Experiment): Boolean = {
    val removed = this.experiments.filter(_.experiment == exp)
    this.experiments.subtractAll(removed)
    this.expCount = this.experiments.size
    removed.nonEmpty
  }

  def checkValidated(): Unit = {
    if (required) {
      if (experiments.isEmpty) {
        this.validated = false
      } else {
        this.validated = !this.experiments.exists(_.experiment.discipline.isEmpty)
      }
    } else {
      this.validated = this.remark.nonEmpty
    }
  }
}
