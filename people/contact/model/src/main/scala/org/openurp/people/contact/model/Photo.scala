package org.openurp.people.contact.model

import org.beangle.data.model.LongId

/**
 * 照片和头像
 */
class Photo extends LongId with Preferred with Typed with UriType with ContactAware {

  var uri: String = _
}