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

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.std.model.Student
import org.openurp.edu.program.model.{AlternativeCourse, MajorAlternativeCourse, StdAlternativeCourse}

class DefaultAlternativeCourseProvider extends AlternativeCourseProvider {
  var entityDao: EntityDao = _

  /**
   * 得到该学生指定专业类型的所有的替代课程
   */
  def getAlternatives(std: Student): collection.Seq[AlternativeCourse] = {
    getStdAlternatives(std) ++ getMajorAlternatives(std)
  }

  /**
   * 得到该学生指定专业类型的个人替代课程
   */
  def getMajorAlternatives(std: Student): collection.Seq[MajorAlternativeCourse] = {
    val query = OqlBuilder.from(classOf[MajorAlternativeCourse], "alternative")
    query.where("alternative.project = :project", std.project);
    query.where("alternative.level = :level", std.level.toLevel);
    query.where("alternative.stdType is null or alternative.stdType = :stdType", std.stdType);
    std.state.foreach(state => {
      query.where(" :grade between alternative.fromGrade  and alternative.toGrade", state.grade);
      query.where("alternative.major is null or alternative.major = :major", state.major)
      state.direction match {
        case None => query.where("alternative.direction is null");
        case _ =>
          query.where(
            "alternative.direction is null or alternative.direction = :direction",
            state.direction.get)
      }
    })
    query.cacheable()
    entityDao.search(query)
  }

  /**
   * 得到该学生指定专业类型的专业替代课程
   *
   */
  def getStdAlternatives(std: Student): collection.Seq[StdAlternativeCourse] = {
    val query = OqlBuilder.from(classOf[StdAlternativeCourse], "alternative")
    query.where("alternative.std=:std", std);
    entityDao.search(query)
  }
}
