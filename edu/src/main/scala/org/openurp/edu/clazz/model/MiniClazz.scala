package org.openurp.edu.clazz.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.model.Course
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.{ProjectBased, User}
import org.openurp.base.std.model.Student

import scala.collection.mutable

@beta
class MiniClazz extends LongId, ProjectBased, Updated, Cloneable, Remark {

  /** 课程序号 */
  var crn: String = _

  /** 课程 */
  var course: Course = _

  /** 授课教师 */
  var teacher: Option[Teacher] = None

  /** 辅导老师 */
  var advisors: mutable.Set[User] = Collections.newSet[User]

  /** 学生名单 */
  var stds: mutable.Set[Student] = Collections.newSet[Student]

  /** 具体排课结果 */
  var activities: mutable.Set[MiniClazzActivity] = Collections.newSet[MiniClazzActivity]

}
