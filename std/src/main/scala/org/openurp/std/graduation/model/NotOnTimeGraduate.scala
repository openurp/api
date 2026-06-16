package org.openurp.std.graduation.model

import org.beangle.commons.json.JsonObject
import org.beangle.data.model.LongId
import org.openurp.base.std.model.GraduateSeason

/**
 * 正常毕业的毕业生
 */
class NotOnTimeGraduate extends LongId {

  /** 毕业界别 */
  var season: GraduateSeason = _

  /** 学籍 */
  var std: Student = _

  /** 是否告知 */
  var informed: Option[Boolean] = None

  /** 告知详情 */
  var inform_details: JsonObject = new JsonObject()

  /** 是否知晓 */
  var acknowledged: Option[Boolean] = None

  /** 知晓时间 */
  var acknowledgedAt: Option[Instant] = None

  /** 个人签名url */
  var stdSignUrl: Option[String] = None
}
