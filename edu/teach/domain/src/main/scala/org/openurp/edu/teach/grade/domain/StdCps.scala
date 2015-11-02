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
package org.openurp.edu.teach.grade.domain

import collection.mutable
import org.beangle.data.model.Updated
import org.beangle.data.model.LongId
import org.openurp.edu.base.model.Student
import org.openurp.base.model.Semester

/**
 * 学生历学期的智育分
 */
class StdCps extends LongId with Updated {
  /**
   *  学生
   */
  var std: Student = _

  /**
   * 每学期智育分
   */
  var semesterCps: mutable.Buffer[StdSemesterCps] = new mutable.ListBuffer[StdSemesterCps]

  /**
   * 每学年智育分
   *
   * @see StdSemesterGpa
   */
  var yearCps: mutable.Buffer[StdYearCps] = new mutable.ListBuffer[StdYearCps]

  /**
   * 总平均绩点
   */
  var gpa: java.lang.Float = _

  /**
   * 平均分
   */
  var ga: java.lang.Float = _

  /**
   * 总学分
   */
  var credits: java.lang.Float = _

  /**
   * 修读学分
   */
  var totalCredits: java.lang.Float = _

  /**
   * 成绩的门数
   */
  var count: Int = _

  /**
   * 查询类缓存
   */
  @transient private var semesterCpsCache: Map[Semester, StdSemesterCps] = _

  @transient private var yearCpsCache: Map[String, StdYearCps] = _

  def this(id: java.lang.Long) {
    this()
    this.id = id
  }

  def this(std: Student) {
    this()
    this.std = std
    this.semesterCps = new collection.mutable.ListBuffer[StdSemesterCps]
    this.yearCps = new collection.mutable.ListBuffer[StdYearCps]
    this.credits = 0f
    this.count = 0
    this.ga = new java.lang.Float(0)
    this.gpa = new java.lang.Float(0)
  }

  def getGpa(semester: Semester): java.lang.Float = {
    val gpterm = getStdTermGpa(semester)
    if (null == gpterm) null else gpterm.gpa
  }

  def getStdTermGpa(semester: Semester): StdSemesterCps = {
    if (null == semesterCpsCache || semesterCpsCache.size != semesterCps.size) {
      semesterCpsCache = semesterCps.map(f => (f.semester, f)).toMap
    }
    semesterCpsCache.get(semester).orNull
  }

  def getYearGpa(schoolYear: String): StdYearCps = {
    if (null == yearCpsCache || yearCpsCache.size != yearCps.size) {
      yearCpsCache = yearCps.map(f => (f.schoolYear, f)).toMap
    }
    yearCpsCache.get(schoolYear).orNull
  }

  def add(stdTermCps: StdSemesterCps) {
    stdTermCps.stdGpa = this
    semesterCps += stdTermCps
  }

  def add(stdYearCps: StdYearCps) {
    stdYearCps.stdGpa = this
    yearCps += stdYearCps
  }
}
