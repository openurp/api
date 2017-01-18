/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
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
package org.openurp.edu.grade.course.model

import collection.mutable
import org.beangle.commons.model.LongId
import org.beangle.commons.model.Updated
import org.openurp.edu.base.model.Student
import org.openurp.base.model.Semester
import org.openurp.edu.base.ProjectBased

trait GpaStat {
  /**
   * 总平均绩点
   * =gp/totalCredits
   */
  var gpa: Float = _

  /**
   * 平均分
   */
  var ga: Float = _

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
  var count: Int = _
}
/**
 * 学生成绩绩点统计
 */
class StdGpa extends LongId with Updated with ProjectBased with GpaStat {
  /**
   *  学生
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

  def this(id: Long) {
    this()
    this.id = id
  }

  def this(std: Student) {
    this()
    this.std = std
    this.semesterGpas = new collection.mutable.ListBuffer[StdSemesterGpa]
    this.yearGpas = new collection.mutable.ListBuffer[StdYearGpa]
    this.credits = 0f
    this.count = 0
    this.ga = new java.lang.Float(0)
    this.gpa = new java.lang.Float(0)
  }

  def getGpa(semester: Semester):  Float = {
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

  def add(stdTermGpa: StdSemesterGpa) {
    stdTermGpa.stdGpa = this
    semesterGpas += stdTermGpa
  }

  def add(stdYearGpa: StdYearGpa) {
    stdYearGpa.stdGpa = this
    yearGpas += stdYearGpa
  }
}
