package org.openurp.people.contact.model

import org.beangle.data.model.LongId

/**
 * 时区
 */
class Timezone extends LongId with Preferred with Typed with ValueTypes with ContactAware {

  /**时区值*/
  var timezone: String = _

  def valuetypes: Set[String] = {
    Set("text", "uri", "utc-offset")
  }
}