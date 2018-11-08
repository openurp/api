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
package org.openurp.edu.graduation.model

import java.time.Instant

import scala.collection.mutable.Buffer

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.openurp.base.model.Department
import org.openurp.edu.base.model.Course
import org.openurp.edu.base.model.Project
import org.openurp.edu.base.model.Semester
import org.openurp.edu.base.model.Squad
import org.openurp.edu.base.model.Teacher

/**
 * 毕业清考任务
 *
 */
class LastMakeupClazz extends LongId {

  /** 清考序号 */
  var crn: String = _

  /**项目*/
  var project: Project = _

  /** 学年学期 */
  var semester: Semester = _

  /** 课程 */
  var course: Course = _

  /** 开课院系 */
  var depart: Department = _

  /** 清考名单 */
  var takers: Buffer[LastMakeupTaker] = Collections.newBuffer[LastMakeupTaker]

  /** 行政班列表 */
  var squads: collection.mutable.Set[Squad] = Collections.newSet[Squad]

  /** 学生人数 */
  var stdCount: Int = _

  /** 是否提交成绩 */
  var gradeSubmited: Boolean = _

  /** 是否发布成绩 */
  var published: Boolean = _

  /** 阅卷老师 */
  var teacher: Option[Teacher] = None

  /**成绩录入时间*/
  var gradeInputAt: Option[Instant] = None

  def mergeWith(makeupClazz: LastMakeupClazz) {
    stdCount += makeupClazz.stdCount
    squads ++= makeupClazz.squads
    for (lastMakeupTake2 <- makeupClazz.takers) {
      val make = new LastMakeupTaker(this, lastMakeupTake2.std, lastMakeupTake2.courseType)
      this.takers += make
    }
  }
}
