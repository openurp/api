package org.openurp.edu.program.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Remark, Updated}
import org.openurp.base.edu.model.Course
import org.openurp.base.model.{EduLevelBased, Project}
import org.openurp.base.std.code.StdType
import org.openurp.base.std.model.Grade

import scala.collection.mutable

/** 免修课程
 * 规定课程在哪些学生范围中，可以免修
 */
@beta
class ExemptCourse extends LongId, EduLevelBased, Updated, Remark {
  /** 起始年级 */
  var fromGrade: Grade = _
  /** 截止年级 */
  var toGrade: Option[Grade] = None

  var course: Course = _

  var stdTypes: mutable.Set[StdType] = Collections.newSet[StdType]
}
