package org.openurp.people.contact.model

import org.beangle.data.model.LongId

/**网页地址*/
class Url extends LongId with Preferred with Typed with UriType with ContactAware {

  var url: String = _

}