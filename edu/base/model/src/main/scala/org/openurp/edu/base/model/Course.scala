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
package org.openurp.edu.base.model

import org.beangle.data.model.LongId
import org.openurp.code.edu.model.EducationLevel
import org.beangle.data.model.Named
import org.openurp.edu.base.ProjectBased
import org.beangle.data.model.annotation.code
import org.beangle.data.model.Coded
import org.openurp.edu.base.code.model.ScoreMarkStyle
import org.beangle.data.model.Updated
import org.openurp.edu.base.code.model.CourseHourType
import org.openurp.edu.base.code.model.CourseType
import org.openurp.edu.base.code.model.CourseCategory
import org.openurp.edu.base.code.model.ExamMode
import java.sql.Date
import org.openurp.edu.base.code.model.CourseAbilityRate
import org.openurp.base.model.Department
import org.beangle.data.model.TemporalOn
import org.openurp.hr.base.model.Staff

/**
 * 课程基本信息 </p>
 * 记录课程代码、名称、学分、课时等基本信息，课程的关键业务属性为课程名称、学分、课时、考核方式等与课程有关的属性，其它类似课程类别、所属部门等
 * 均可以看作非关键属性。 </p> 如课程不要求记录学分、不做考核要求、不计算绩点等额外要求需要培养方案、成绩等环节进行额外处理，不在课程部分进行规定。
 * <p>
 * 课程的学历层次可以不加指定，为空时表示适用与对应项目下的所有学历层次。
 *
 * @depend - - - CourseCategory
 * @depend - - - Department
 * @depend - - - CourseType
 * @depend - - - ExamMode
 * @author cheneystar
 * @author chaostone
 * @since 2008-09-24
 */
class Course extends LongId with ProjectBased with Ordered[Course] with Updated with TemporalOn with Coded with Named {
  /**课程英文名*/
  var enName: String = _
  /** 学历层次 */
  var educationLevel: EducationLevel = _
  /**课程种类代码*/
  var category: CourseCategory = _
  /**学分*/
  var credits: java.lang.Float = _
  /** 学时/总课时 */
  var period: Int = _
  /**课程类型*/
  var courseType: CourseType = _
  /** 分类课时 */
  var hours: collection.mutable.Seq[CourseHour] = new collection.mutable.ListBuffer[CourseHour]
  /** 周数*/
  var weeks: Int = _
  /**周课时*/
  var weekHour: Int = _
  /**院系*/
  var department: Department = _
  /** 考试方式 */
  var examMode: ExamMode = _
  /** 成绩记录方式 */
  var markStyle: ScoreMarkStyle = _

  /** 能力等级 */
  var abilityRates: collection.mutable.Set[CourseAbilityRate] = new collection.mutable.HashSet[CourseAbilityRate]
  /**针对专业*/
  var majors: collection.mutable.Set[Major] = new collection.mutable.HashSet[Major]
  /**排除专业*/
  var xmajors: collection.mutable.Set[Major] = new collection.mutable.HashSet[Major]
  //  /**先修课程*/
  //  var prerequisites: collection.mutable.Set[Course] = new collection.mutable.HashSet[Course]

  var textbooks: collection.mutable.Set[Textbook] = new collection.mutable.HashSet[Textbook]

  var teachers: collection.mutable.Set[Staff] = new collection.mutable.HashSet[Staff]
  /**课程备注*/
  var remark: String = _
  /** 是否计算绩点 **/
  var calgp: Boolean = _

  override def compare(other: Course): Int = {
    code.compareTo(other.code)
  }

  def this(id: java.lang.Long, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = enName
  }
}

/**
 * 课程分类课时信息
 *
 * @author chaostone
 */

class CourseHour extends LongId {
  var course: Course = _
  var period: Int = _
  var weekHour: Int = _
  var weeks: Int = _
  var hourType: CourseHourType = _
}
