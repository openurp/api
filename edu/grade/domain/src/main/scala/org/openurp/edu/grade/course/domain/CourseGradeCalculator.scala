/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.course.domain

import org.openurp.edu.grade.course.model.CourseGrade

/**
 * 成绩计算器
 *
 * @author chaostone
 */
trait CourseGradeCalculator {

  /**
   * 计算最终成绩
   * 一般是计算最终得分 MAX(GA,发布的补考成绩,缓考总评)+bonus
   *
   * @param grade
   * @return 计算结果,但不改动成绩
   */
  def calcScore(grade: CourseGrade): java.lang.Float

  /**
   * 计算总评成绩
   */
  def calcEndGa(grade: CourseGrade): java.lang.Float

  /**
   * 计算总评成绩
   */
  def calcDelayGa(grade: CourseGrade): java.lang.Float

  /**
   * 计算总评成绩
   */
  def calcMakeupGa(grade: CourseGrade): java.lang.Float
  /**
   * 计算总评成绩,最终成绩,是否通过和绩点
   *
   * @param grade
   */
  def calc(grade: CourseGrade): Unit

  /**
   * 更新最终
   */
  def updateScore(grade: CourseGrade, score: java.lang.Float): Unit
}
