package org.openurp.people.contact.model

import org.beangle.data.model.LongId

/**
 * 相关联系人
 */
class Related extends LongId with Preferred with Typed with UriTextType with ContactAware {
  var related: String = _
}

object Related {
  val Types = Array("contact", "acquaintance", "friend", "met", "co-worker", "colleague", "co-resident", "neighbor", "child", "parent", "sibling", "spouse", "kin", "muse", "crush", "date", "sweetheart", "me", "agent", "emergency")
}