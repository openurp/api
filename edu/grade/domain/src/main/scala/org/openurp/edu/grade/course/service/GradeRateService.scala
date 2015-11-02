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
package org.openurp.edu.grade.course.service

import org.openurp.edu.base.code.model.ScoreMarkStyle
import org.openurp.edu.base.model.Project
import org.openurp.edu.grade.code.model.GradeType
import org.openurp.edu.grade.model.{ Grade, GradeRateConfig }

/**
 * 绩点规则服务类
 * <p>
 * 提供以下服务
 * <li>转换成绩 convert</li>
 * <li>计算绩点 calGp</li>
 * <li>判断是否通过</li>
 * <li>查询支持的记录方式</li>
 */
trait GradeRateService {

  /**
   * 计算绩点
   */
  def calcGp(grade: Grade, gradeType: GradeType): java.lang.Float

  /**
   * 将字符串按照成绩记录方式转换成数字.<br>
   * 空成绩将转换成null
   */
  def convert(score: String, scoreMarkStyle: ScoreMarkStyle, project: Project): java.lang.Float

  /**
   * 将字符串按照成绩记录方式转换成数字.<br>
   * 空成绩将转换成null
   */
  def convert(score: java.lang.Float, scoreMarkStyle: ScoreMarkStyle, project: Project): String

  /**
   */
  def isPassed(score: java.lang.Float, scoreMarkStyle: ScoreMarkStyle, project: Project): Boolean

  /**
   * 查询项目对应的记录方式的配置
   *
   */
  def getConfig(project: Project, scoreMarkStyle: ScoreMarkStyle): GradeRateConfig

  /**
   * 查询该项目对应的记录方式
   */
  def getMarkStyles(project: Project): Seq[ScoreMarkStyle]
}
