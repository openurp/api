package org.openurp.people.contact.model

import org.beangle.data.model.LongId
import org.beangle.data.model.Named

/**
 * 单位
 */
class Org extends LongId with Preferred with Typed with Named with TextType  with ContactAware {

  /**部门*/
  var unit: String = _
}