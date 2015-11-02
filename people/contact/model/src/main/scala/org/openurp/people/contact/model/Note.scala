package org.openurp.people.contact.model

import org.beangle.data.model.LongId
import org.beangle.data.model.Named

/**
 * 备注
 */
class Note extends LongId with Preferred with Typed with TextType with ContactAware {
  var note: String = _
}