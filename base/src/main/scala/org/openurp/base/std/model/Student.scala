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

package org.openurp.base.std.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.pojo.*
import org.beangle.data.model.{Component, LongId}
import org.openurp.base.edu.model.*
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.*
import org.openurp.code.edu.model.{EducationLevel, StudyType}
import org.openurp.code.person.model.Gender
import org.openurp.code.std.model.{StdLabel, StdLabelType, StdType, StudentStatus}

import java.time.LocalDate
import scala.collection.mutable

/**
 * 学籍信息
 * </p>
 * 学籍信息记录了四部分内容： <li>基本内容 学号、姓名、英文名(拼音)、性别</li> <li>培养内容 项目、年级、院系、专业、方向、班级、培养层次、学习形式、学生分类标签</li> <li>
 * 培养时间 录取时间、入学时间、预计毕业时间、学制</li> <li>学籍状态日志 各时段的是否在校、专业、方向以及学籍状态</li>
 *
 * 入学日期 + 学制 = 预计毕业日期（延长和提前情况另算）
 * 入学日期 + 最长学习年限 = 学籍有效最晚日期
 * beginOn 入学日期 / endOn 预计离校日期
 *
 * @author chaostone
 * @since 2005
 */
class Student extends LongId, Coded, Named, EduLevelBased, Updated, Remark, DateRange {

  /** 用户 */
  var user: User = _

  /** 基本信息 */
  var person: Person = _

  /** 性别 */
  var gender: Gender = _

  /** 学生类别 所在项目内的学生类别 */
  var stdType: StdType = _

  /** 学籍状态 */
  var state: Option[StudentState] = None

  /** 状态 */
  var states = Collections.newBuffer[StudentState]

  /** 学生分类标签 */
  var labels = Collections.newMap[StdLabelType, StdLabel]

  /** 学制（允许0.5年出现） */
  var duration: Float = _

  /** 最晚离校日期
   * 最晚日期 =入学日期 + 最长学习年限
   */
  var maxEndOn: LocalDate = _

  /** 预计毕业日期 */
  var graduateOn: LocalDate = _

  /** 是否有学籍 */
  var registed: Boolean = _

  /** 学习形式 全日制/业余/函授 */
  var studyType: StudyType = _

  /** 导师 */
  var tutors: mutable.Buffer[StudentTutor] = Collections.newBuffer[StudentTutor]

  /** 是否延期毕业 */
  var graduationDeferred: Boolean = _

  def calcCurrentState(): Unit = {
    this.state = Some(stateOn(LocalDate.now()))
  }

  def stateOn(date: LocalDate): StudentState = {
    this.states.find(_.within(date)) match {
      case st@Some(s) => s
      case None => this.states.maxBy(_.endOn)
    }
  }

  def isInschool(beginOn: LocalDate, endOn: LocalDate): Boolean = {
    this.states.exists(x => x.beginOn.isBefore(endOn) && beginOn.isBefore(x.endOn) && x.inschool)
  }

  def grade: Grade = state.get.grade

  /** 所在部门
   *
   * @return
   */
  def department: Department = state.get.department

  /** 所在学院
   *
   * @return
   */
  def college: Department = {
    var depart = state.get.department
    val departs = Collections.newSet[Department]
    while (depart.parent.nonEmpty && !departs.contains(depart)) {
      departs.addOne(depart)
      val parent = depart.parent.get
      if (parent.name == parent.school.name) {
        departs.addOne(parent)
      } else {
        depart = parent
      }
    }
    depart
  }

  /** 所在专业 */
  def major: Major = state.get.major

  /** 所在专业方向 */
  def direction: Option[Direction] = state.get.direction

  /** 学生所在班级 */
  def squad: Option[Squad] = state.get.squad

  def description: String = s"$code $name ${department.shortName.getOrElse(department.name)}"

  /** 入学时的学科专业代码 */
  def disciplineCode: String = state.get.major.getDisciplineCode(beginOn)

  /** 入学时的学科专业名称 */
  def disciplineName: String = state.get.major.getDisciplineName(beginOn)

  def updateTutors(teachers: Iterable[Teacher], ship: Tutorship): Unit = {
    val newTutors = teachers.toSet
    newTutors foreach { t =>
      if (!this.tutors.exists(x => x.tutor == t && x.tutorship == ship)) {
        val nt = new StudentTutor(this, t, ship)
        this.tutors.addOne(nt)
      }
    }
    val removed = this.tutors.filter(x => x.tutorship == ship && !newTutors.contains(x.tutor))
    this.tutors.subtractAll(removed)
  }

  /** 专业导师 */
  def majorTutors: collection.Seq[Teacher] = {
    tutors.filter(_.tutorship == Tutorship.Major).map(_.tutor)
  }

  /** 论文指导老师 */
  def thesisTutor: Option[Teacher] = {
    tutors.find(_.tutorship == Tutorship.Thesis).map(_.tutor)
  }

  /** 导师姓名 */
  def majorTutorNames: String = {
    tutors.filter(_.tutorship == Tutorship.Major).map(_.tutor.name).mkString(",")
  }
}

/**
 * 学籍状态
 * </p>
 * 学籍状态日志记录从起始时间到结束时间之间的学籍状态。主要记录学生的 <li>年级</li> <li>管理院系</li> <li>专业</li> <li>方向</li> <li>行政班级</li>
 * <li>是否在校</li> <li>学籍状态</li> [beginOn,endOn]
 *
 * @author chaostone
 */
class StudentState extends LongId, StdEnrollment, DateRange, Remark {

  /** 学生 */
  var std: Student = _

  /** 年级 */
  var grade: Grade = _

  /** 管理院系 */
  var department: Department = _

  /** 专业 */
  var major: Major = _

  /** 专业方向 */
  var direction: Option[Direction] = None

  /** 行政班级 */
  var squad: Option[Squad] = None

  /** 是否在校 */
  var inschool: Boolean = _

  /** 学籍状态 */
  var status: StudentStatus = _

  /** 校区 */
  var campus: Campus = _
}

/**
 * 学生范围
 */
class StudentScope extends Component {
  /** 年级 */
  var grades: String = _
  /** 项目 */
  var project: Project = _
  /** 培养层次集合 */
  var levels: mutable.Set[EducationLevel] = Collections.newSet[EducationLevel]
  /** 学生类别集合 */
  var stdTypes: mutable.Set[StdType] = Collections.newSet[StdType]
  /** 部门集合 */
  var departments: mutable.Set[Department] = Collections.newSet[Department]
  /** 专业集合 */
  var majors: mutable.Set[Major] = Collections.newSet[Major]
  /** 专业方向集合 */
  var directions: mutable.Set[Direction] = Collections.newSet[Direction]
}

/**
 * 学籍注册信息
 */
trait StdEnrollment {

  /** 年级 表示现在年级，不同于入学时间 */
  def grade: Grade

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
 * 基于学生信息的实体
 *
 * @author Administrator
 */
trait StudentBased {

  var std: Student = _
}
