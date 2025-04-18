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

package org.openurp.edu.grade.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.{ProjectBased, Semester}
import org.openurp.base.std.model.Student

import scala.collection.mutable

trait GpaStat {
  /**
   * 总平均绩点 gp/totalCredits
   */
  var gpa: Double = _

  /**
   * 平均分
   */
  var ga: Double = _

  /**
   * 修读总学分
   */
  var totalCredits: Float = _

  /**
   * 获得总学分
   */
  var credits: Float = _

  /**
   * 成绩的门数
   */
  var gradeCount: Int = _
}

/**
 * 学生成绩绩点统计
 */
class StdGpa extends LongId, Updated, ProjectBased, GpaStat {
  /**
   * 学生
   */
  var std: Student = _

  /**
   * 每学期平均绩点
   */
  var semesterGpas: mutable.Buffer[StdSemesterGpa] = new mutable.ListBuffer[StdSemesterGpa]

  /**
   * 每学年平均绩点
   *
   * @see StdSemesterGpa
   */
  var yearGpas: mutable.Buffer[StdYearGpa] = new mutable.ListBuffer[StdYearGpa]

  /**
   * 查询类缓存
   */
  @transient private var semesterGpaCache: Map[Semester, StdSemesterGpa] = _

  @transient private var yearGpaCache: Map[String, StdYearGpa] = _

  def this(id: Long) = {
    this()
    this.id = id
  }

  def this(std: Student) = {
    this()
    this.std = std
    this.semesterGpas = new collection.mutable.ListBuffer[StdSemesterGpa]
    this.yearGpas = new collection.mutable.ListBuffer[StdYearGpa]
    this.credits = 0f
    this.gradeCount = 0
    this.ga = 0d
    this.gpa = 0d
  }

  def getGpa(semester: Semester): Double = {
    val gpterm = getStdTermGpa(semester)
    if (null == gpterm) 0 else gpterm.gpa
  }

  def getStdTermGpa(semester: Semester): StdSemesterGpa = {
    if (null == semesterGpaCache || semesterGpaCache.size != semesterGpas.size) {
      semesterGpaCache = semesterGpas.map(f => (f.semester, f)).toMap
    }
    semesterGpaCache.get(semester).orNull
  }

  def getYearGpa(schoolYear: String): StdYearGpa = {
    if (null == yearGpaCache || yearGpaCache.size != yearGpas.size) {
      yearGpaCache = yearGpas.map(f => (f.schoolYear, f)).toMap
    }
    yearGpaCache.get(schoolYear).orNull
  }

  def add(stdTermGpa: StdSemesterGpa): Unit = {
    stdTermGpa.stdGpa = this
    semesterGpas += stdTermGpa
  }

  def add(stdYearGpa: StdYearGpa): Unit = {
    stdYearGpa.stdGpa = this
    yearGpas += stdYearGpa
  }
}
