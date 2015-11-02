package org.openurp.people.contact.model

import org.beangle.data.model.LongId

/**
 * 电子邮件
 */
class Email extends LongId with Preferred with Typed with TextType with ContactAware {
  var email: String = _
}