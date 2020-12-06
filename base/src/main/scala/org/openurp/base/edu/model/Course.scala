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
package org.openurp.base.edu.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.Coded
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.pojo.Remark
import org.beangle.data.model.pojo.TemporalOn
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Department
import org.openurp.code.edu.model.AcademicLevel
import org.openurp.code.edu.model.ExamMode
import org.openurp.code.edu.model.GradingMode
import org.openurp.base.edu.ProjectBased
import org.openurp.base.edu.code.model.CourseAbilityRate
import org.openurp.base.edu.code.model.CourseHourType
import org.openurp.base.edu.code.model.CourseType
import org.openurp.base.edu.code.model.CourseCategory

/**
 * 课程基本信息 </p>
 * 记录课程代码、名称、学分、课时等基本信息，课程的关键业务属性为课程名称、学分、课时、考核方式等与课程有关的属性，其它类似课程类别、所属部门等
 * 均可以看作非关键属性。 </p> 如课程不要求记录学分、不做考核要求、不计算绩点等额外要求需要培养方案、成绩等环节进行额外处理，不在课程部分进行规定。
 * <p>
 * 课程的学历层次可以不加指定，为空时表示适用与对应项目下的所有学历层次。
 * @author chaostone
 * @since 2008-09-24
 */
class Course extends LongId with ProjectBased with Ordered[Course] with Updated
  with TemporalOn with Coded with Named with Remark {
  /** 课程英文名 */
  var enName: Option[String] = None
  /** 学历层次 */
  var levels = Collections.newSet[AcademicLevel]
  /** 学分 */
  var credits: Float = _
  /** 学时/总课时 */
  var creditHours: Int = _
  /** 课程类型 */
  var courseType: CourseType = _
  /** 课程大类 */
  var category: Option[CourseCategory] = None
  /** 分类课时 */
  var hours = Collections.newBuffer[CourseHour]
  /** 周数 */
  var weeks: Int = _
  /** 周课时 */
  var weekHours: Int = _
  /** 院系 */
  var department: Department = _
  /** 考试方式 */
  var examMode: ExamMode = _
  /** 成绩记录方式 */
  var gradingModes = Collections.newSet[GradingMode]
  /** 能力等级 */
  var abilityRates = Collections.newSet[CourseAbilityRate]
  /** 针对专业 */
  var majors = Collections.newSet[Major]
  /** 排除专业 */
  var xmajors = Collections.newSet[Major]
  /** 推荐教材 */
  var textbooks = Collections.newSet[Textbook]
  /** 教师 */
  var teachers = Collections.newSet[Teacher]
  /** 是否计算绩点 * */
  var calgp: Boolean = _
  /** 是否实践课程 */
  var practical: Boolean = _
  /** 是否有补考 */
  var hasMakeup: Boolean = _
  /** 教研室 */
  var teachingGroup: Option[TeachingGroup] = None

  override def compare(other: Course): Int = {
    code.compareTo(other.code)
  }

  def this(id: Long, code: String, name: String, enName: String) = {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Option(enName)
  }
}

/**
 * 课程分类课时信息
 * @author chaostone
 */

class CourseHour extends LongId {
  var course: Course = _
  var creditHours: Int = _
  var weeks: Int = _
  var hourType: CourseHourType = _
}
