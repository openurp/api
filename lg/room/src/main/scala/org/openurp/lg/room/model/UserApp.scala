package org.openurp.lg.room.model

import org.beangle.data.model.LongId
import org.beangle.data.model.Named

/**
 * 房间的使用系统
 */
class UserApp extends LongId with Named {

  /**活动明细url*/
  var activityUrl: String = _
}