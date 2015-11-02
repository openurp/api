package org.openurp.people.contact.model

import org.beangle.data.model.LongId

/**
 * 即时消息
 * @see https://tools.ietf.org/html/rfc6350#section-6.4.3
 * @see https://tools.ietf.org/html/rfc4770
 */
class Impp extends LongId with Preferred with Typed with UriType with ContactAware {
  /**
   * Presence Protocol
   *  IMPP;TYPE=personal,pref:im:alice@example.com
   */
  var impp: String = _

}