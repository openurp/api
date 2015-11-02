package org.openurp.people.contact.model

import org.beangle.data.model.LongId
import org.beangle.data.model.Named

/**
 * 扩展信息
 */
class Xtended extends LongId with Preferred with Typed  with TextType with ContactAware {
  var xname: String = _
  var xvalue: String = _
}