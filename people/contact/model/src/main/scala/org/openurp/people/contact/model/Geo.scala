package org.openurp.people.contact.model

import org.beangle.data.model.LongId

/**
 * 地理方位
 */
class Geo extends LongId with Preferred with Typed with UriType with ContactAware {

  /**纬度*/
  var latitude: Double = _

  /**经度*/
  var longitude: Double = _
}