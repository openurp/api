package org.openurp.base.config.service

import org.beangle.commons.json.JsonObject
import org.openurp.base.config.model.BusinessSetting
import org.openurp.base.model.Project

trait BusinessSettingService {

  def getSetting[T](project: Project, business: String, profileId: String, mapper: JsonMapper[T]): Option[T]

  def update(project: Project, business: String, profileId: String, settings: JsonObject): Unit

  def get(project: Project, business: String, profileId: String): Option[BusinessSetting]
}
