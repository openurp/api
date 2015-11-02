package org.openurp.people.contact.model

import org.beangle.data.model.LongId

/**
 * 电话
 */
class Telephone extends LongId with Preferred with Typed   with UriTextType with ContactAware {
  /**电话号码*/
  var tel: String = _
}

object Telephone {
  val Types = Array("text", "voice", "fax", "cell", "video", "pager", "textphone", "home", "work")
}