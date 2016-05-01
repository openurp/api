package org.openurp.edu.base.service

import org.openurp.code.BaseCode
import org.openurp.edu.base.model.Project

trait ProjectCodeService {

  def getCodes[T <: BaseCode](project: Project, clazz: Class[T]): Seq[T]

  def getCode[T <: BaseCode](clazz: Class[T], id: Int): T
}