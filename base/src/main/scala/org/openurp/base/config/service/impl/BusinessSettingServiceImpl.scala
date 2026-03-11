package org.openurp.base.config.service.impl

import org.beangle.commons.json.JsonObject
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.config.model.BusinessSetting
import org.openurp.base.config.service.BusinessSettingService
import org.openurp.base.model.Project

import java.time.Instant

class BusinessSettingServiceImpl extends BusinessSettingService {

  var entityDao: EntityDao = _

  override def update(project: Project, business: String, profileId: String, settings: JsonObject): Unit = {
    val setting = get(project, business, profileId).getOrElse {
      val s = new BusinessSetting
      s.project = project
      s.business = business
      s.profileId = profileId
      s
    }
    setting.updatedAt = Instant.now
    setting.settings = settings
    entityDao.saveOrUpdate(setting)
  }

  def get(project: Project, business: String, profileId: String): Option[BusinessSetting] = {
    val builder = OqlBuilder.from(classOf[BusinessSetting], "bs")
    builder.where("bs.business = :business", business)
    builder.where("bs.profileId = :profileId", profileId)
    builder.where("bs.project = :project", project)
    builder.cacheable()
    entityDao.search(builder).headOption
  }

  def getSetting[T](project: Project, business: String, profileId: String, mapper: JsonMapper[T]): Option[T] = {
    get(project, business, profileId).map(x => mapper.fromJson(x.settings))
  }
}
