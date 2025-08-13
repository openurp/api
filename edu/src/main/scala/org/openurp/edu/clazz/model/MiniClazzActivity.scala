package org.openurp.edu.clazz.model

import org.beangle.commons.lang.time.WeekTime
import org.beangle.data.model.LongId
import org.openurp.base.hr.model.Teacher
import org.openurp.base.model.User

/** 小课上课安排
 */
class MiniClazzActivity extends LongId {

  /** 教学任务 */
  var miniclazz: MiniClazz = _

  /** 上课时间 */
  var time: WeekTime = _

  /** 开始节次 */
  var beginUnit: Short = _

  /** 结束节次 */
  var endUnit: Short = _

  /** 授课教师 */
  var teacher: Option[Teacher] = None

  /** 辅导老师1 */
  var advisor1: Option[User] = None

  /** 辅导老师2 */
  var advisor2: Option[User] = None
}
