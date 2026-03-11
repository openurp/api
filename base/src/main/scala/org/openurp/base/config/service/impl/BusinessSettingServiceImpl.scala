/*
 * Copyright (C) 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openurp.base.config.service.impl

import org.beangle.commons.json.{JsonMapper, JsonObject}
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
