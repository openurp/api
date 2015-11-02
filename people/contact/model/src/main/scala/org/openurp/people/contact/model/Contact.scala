package org.openurp.people.contact.model

import java.sql.Date

import org.beangle.data.model.{ LongId, Updated }
import org.openurp.people.base.model.Person

/**
 * 联系方式
 * @see https://tools.ietf.org/html/rfc6350
 */
class Contact extends LongId with Updated {

  var person: Person = _

  var revision: Long = _

  var uuid: String = _

  var sortAs: String = _

  var anniversary: Date = _
}