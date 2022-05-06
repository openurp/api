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

package org.openurp.edu.program.domain

import org.beangle.data.dao.EntityDao
import org.openurp.base.std.model.Student
import org.openurp.edu.program.model._

class DefaultCoursePlanProvider extends CoursePlanProvider {

  var programProvider: ProgramProvider = _
  var entityDao: EntityDao = _

  /**
   * 获得原始专业培养计划
   */
  override def getMajorPlan(student: Student): Option[MajorPlan] = {
    programProvider.getProgram(student) match {
      case Some(p) => getMajorPlan(p)
      case None => None
    }
  }

  /**
   * 获得专业培养计划
   */
  override def getExecutionPlan(student: Student): Option[ExecutionPlan] = {
    programProvider.getProgram(student) match {
      case Some(p) => getExecutionPlan(p, student)
      case None => None
    }
  }

  /**
   * 获得单个学生的个人计划
   */
  def getStdPlan(student: Student): Option[StdPlan] = {
    entityDao.findBy(classOf[StdPlan], "std", List(student)).headOption
  }

  /**
   * 获得学生的计划
   *
   * @param std
   * @return
   */
  def getCoursePlan(std: Student): Option[CoursePlan] = {
    getStdPlan(std) match {
      case Some(stdPlan) => Some(stdPlan)
      case None =>
        programProvider.getProgram(std) match {
          case Some(p) =>
            getExecutionPlan(p, std).orElse(getMajorPlan(p))
          case None => None
        }
    }
  }

  private def getMajorPlan(p: Program): Option[MajorPlan] = {
    entityDao.findBy(classOf[MajorPlan], "program", List(p)).headOption
  }

  private def getExecutionPlan(p: Program, student: Student): Option[ExecutionPlan] = {
    val plans = entityDao.findBy(classOf[ExecutionPlan], "program", List(p))
    val matched = plans.filter { p =>
      p.department == student.state.get.department &&
        (p.stdType.isEmpty || p.stdType.get == student.stdType) &&
        (p.campus.isEmpty || p.campus.get == student.state.get.campus)
    }
    matched.headOption
  }
}
