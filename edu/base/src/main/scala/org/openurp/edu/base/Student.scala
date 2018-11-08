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
package org.openurp.edu.base

import org.openurp.base.model.Department
import org.openurp.code.edu.model.EducationLevel
import org.openurp.code.edu.model.StudentStatus
import org.openurp.edu.base.model.Direction
import org.openurp.edu.base.model.Major
import org.openurp.edu.base.model.Squad
import org.openurp.edu.base.model.Student

/**
 * 学籍注册信息
 */
trait StdEnrollment {

  /** 年级 表示现在年级，不同于入学时间 */
  def grade: String

  /** 管理院系 行政管理院系 */
  def department: Department

  /** 专业 当前修读专业 */
  def major: Major

  /** 方向 当前修读方向 */
  def direction: Option[Direction]

  /** 行政班级 */
  def squad: Option[Squad]

  /** 是否在校 */
  def inschool: Boolean

  /** 学籍状态 */
  def status: StudentStatus

}

/**
 * 学籍信息
 * </p>
 * 学籍信息记录了四部分内容： <li>基本内容 学号、姓名、英文名(拼音)、性别</li> <li>培养内容 项目、年级、院系、专业、方向、班级、培养层次、学习形式、学生分类标签</li> <li>
 * 培养时间 录取时间、入学时间、预计毕业时间、学制</li> <li>学籍状态日志 各时段的是否在校、专业、方向以及学籍状态</li>
 *
 * @depend - - - Gender
 * @depend - - - Department
 * @depend - - - Major
 * @depend - - - Direction
 * @depend - - - StdType
 * @depend - - - StudyType
 * @depend - - - EducationType
 * @has 1..* has 1..* StdLabel
 * @author chaostone
 * @since 2005
 */

/**
 * 学籍状态
 * </p>
 * 学籍状态日志记录从起始时间到结束时间之间的学籍状态。主要记录学生的 <li>年级</li> <li>管理院系</li> <li>专业</li> <li>方向</li> <li>行政班级</li>
 * <li>是否在校</li> <li>学籍状态</li> [beginOn,endOn)
 *
 * @author chaostone
 */

/**
 * 基于学生信息的实体
 *
 * @author Administrator
 */
trait StudentBased {

  var std: Student = _
}

/**
 * 基于培养层次的实体接口
 * </p>
 * 基于项目和培养层次的实体接口，标准化了培养层次的属性名称。
 *
 * @see Squad
 * @see Student
 * @author chaostone
 */

trait EduLevelBased extends ProjectBased {
  var level: EducationLevel = _
}
