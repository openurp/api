package org.openurp.code.sin.model

import org.beangle.data.model.annotation.code
import org.openurp.code.BaseCodeBean

/**
 * 刊物
 */
@code("industry")
class Publication extends BaseCodeBean {

  var grade: PublicationGrade = _
}

/**
 * 刊物级别
 */
@code("industry")
class PublicationGrade extends BaseCodeBean {

}

/**
 * 出版社
 */
@code("industry")
class Press extends BaseCodeBean{
   var grade: PressGrade = _
}

/**
 * 出版社别
 * 参见教育部标准JY/T 1001 4.6.1
 */
@code("industry")
class PressGrade extends BaseCodeBean
