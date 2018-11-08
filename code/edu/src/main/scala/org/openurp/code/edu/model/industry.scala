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
package org.openurp.code.edu.model

import org.beangle.data.model.annotation.code
import org.openurp.code.CodeBean

/**
 * 录取类别
 * 参见教育部标准JY/T 1001 4.2.28.3
 */
@code("industry")
class AdmissionType extends CodeBean

/**
 * 活动类型
 *  参考教育部标准JY/T 1001 4.3.4（教室占用情况）
 */
@code("industry")
class ActivityType extends CodeBean

/**
 * 教室类型
 * 参见教育部标准JY/T 1001 4.3.3
 */
@code("industry")
class ClassroomType extends CodeBean
/**
 * 学科目录
 *
 * 一般有：本科学科目录、研究生学术性学位目录、研究生专业性学位目录
 */
@code("industry")
class DisciplineCatalog extends CodeBean
/**
 * 学科
 * （来自本专科科学科目录、研究生学术性学位目录、研究生专业性学位目录的学科）<br>
 * 一般认为4位代码的是一级学科，2位代码的是二级学科，但实际上可能没有一级学科、二级学科这种叫法
 *
 * @author chaostone
 */
@code("industry")
class Discipline extends CodeBean {
  var category: DisciplineCategory = _
  var catalog: DisciplineCatalog = _
}

/**
 * 入学方式
 * 参见教育部推荐标准JY/T 1001 4.2.32
 */
@code("industry")
class EnrollMode extends CodeBean

/**
 * 培养方式
 * 参见教育部标准JY/T 1001 4.2.30
 */
@code("industry")
class EducationMode extends CodeBean

/**
 * 培养层次
 */
@code("industry")
class EducationLevel extends CodeBean {
  var fromLevel: AcademicLevel = _
  var toLevel: AcademicLevel = _
}
/**
 * 教育类别
 */
@code("industry")
class EduCategory extends CodeBean {
}
/**
 * 留学生HSK等级
 * 参见教育部标准JY/T 1001 4.5.4
 */
@code("industry")
class HskLevel extends CodeBean {
  var grade: Int = _
}

/**
 * 科研机构
 * @see http://www.stats.edu.cn/rjgx/dm/%E6%99%AE%E9%80%9A%E9%AB%98%E6%A0%A1%E4%BB%A3%E7%A0%81.htm
 */
@code("industry")
class Institution extends CodeBean

/**
 * 学生处分类型
 * 参见教育部标准JY/T 1001 4.2.3
 */
@code("industry")
class StudentPunishmentType extends CodeBean

/**
 * 学籍异动类别
 * 参见教育部标准JY/T 1001 4.2.39
 */
@code("industry")
class StudentAlterType extends CodeBean

/**
 * 学籍异动原因
 * 参见教育部标准JY/T 1001 4.2.40
 */
@code("industry")
class StudentAlterReason extends CodeBean

/**
 * 学生当前状态
 * 参见教育部标准JY/T 1001 4.2.42
 */
@code("industry")
class StudentStatus extends CodeBean

/**
 * 高考科目
 * 参见教育部标准JY/T 1001 4.2.8
 */
@code("industry")
class UeeSubjectType extends CodeBean

/**
 * 修课类别
 * （重修、增修、免修不免试、主修，选修）
 *
 * @author chaostone
 * @since 2005-12-2
 */
@code("industry")
class CourseTakeType extends CodeBean {
  def this(id: Int, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Some(enName)
  }
}
object CourseTakeType {

  /** 正常修读  */
  val Normal = 1
  /** 重修 */
  val Repeat = 3
  /** 免修 */
  val Exemption = 5
}

/**
 * 考试情况
 * 正常、作弊、旷考等
 */
@code("industry")
class ExamStatus extends CodeBean {
  /**
   * 是否参加考试
   */
  var attended: Boolean = _
  /**
   * 是否需要参加下一次缓考
   */
  var deferred: Boolean = _
  /**
   * 是否有作弊行为
   */
  var cheating: Boolean = _

  def this(id: Int) {
    this()
    this.id = id
  }
}

object ExamStatus {

  /** 正常 */
  val Normal = 1;

  /**缺考*/
  val Absent = 3;
}

/**
 * 成绩记录方式
 */
@code("industry")
class GradingMode extends CodeBean {

  var numerical: Boolean = _

  def this(id: Int) {
    this()
    this.id = id
  }
  def this(id: Int, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Some(enName)
  }
}

object GradingMode {
  val Percent = 1
}

/**
 * 上下午时段
 */
@code("industry")
class DayPart extends CodeBean
/**
 * 成绩类型
 *
 * @author chaostone
 * @since 2005-9-7
 */
@code("industry")
class GradeType extends CodeBean {
  var examType: Option[ExamType] = None

  def this(id: Int) {
    this()
    this.id = id
  }

  def this(id: Int, code: String, name: String, enName: String) {
    this()
    this.id = id
    this.code = code
    this.name = name
    this.enName = Some(enName)
  }

  def isGa: Boolean = {
    id == GradeType.EndGa || id == GradeType.MakeupGa || id == GradeType.DelayGa
  }

  def isMakeupOrDeplay: Boolean = {
    id == GradeType.Makeup || id == GradeType.Delay
  }

}

object GradeType {
  val Final = 0

  val Middle = 1
  val End = 2
  val Usual = 3
  val Makeup = 4
  val Delay = 6

  val EndGa = 7
  val DelayGa = 8
  val MakeupGa = 9
}

/**
 * 考试类型
 */
object ExamType {

  /** 期末考试 */
  val Final = 1

  /** 期中考试 */
  val Midterm = 2

  /** 补考 */
  val Makeup = 3

  /** 缓考 */
  val Delay = 4
}

@code("industry")
class ExamType extends CodeBean {

  /**是否是缓考*/
  var deferred: Boolean = _

  def this(id: Int) {
    this()
    this.id = id
  }

  def this(id: Int, code: String, name: String, enName: String) {
    this(id)
    this.code = code
    this.name = name
    this.enName = Some(enName)
  }
}

@code("industry")
class ElectionMode extends CodeBean

object ElectionMode {
  /** 指定 */
  val Assigned = 1

  /** 自选  */
  val SelfChoose = 2
}

/**
 * 考核方式
 */
@code("industry")
class ExamMode extends CodeBean

/**
 * 考试方式
 */
@code("industry")
class ExamForm extends CodeBean

/**
 * 授课语言类型
 */
@code("industry")
class TeachLangType extends CodeBean
