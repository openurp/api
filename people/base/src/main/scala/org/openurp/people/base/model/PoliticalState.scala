package org.openurp.people.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.Updated
import org.openurp.code.person.model.PoliticalStatus
import org.beangle.data.model.TemporalOn

/**
 * 政治面貌
 */
class PoliticalState extends LongId with Updated with TemporalOn {

  /**人员*/
  var person: Person = _

  /**政治面貌 */
  var status: PoliticalStatus = _

}