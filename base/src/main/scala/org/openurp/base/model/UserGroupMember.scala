package org.openurp.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated

/** 用户组成员
 */
class UserGroupMember extends LongId, Updated {

  var group: UserGroup = _

  var user: User = _

}
