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

package org.openurp.base.edu.service.impl

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.base.edu.model.TimeSetting
import org.openurp.base.edu.service.TimeSettingService
import org.openurp.base.model.{Campus, Project, Semester}

class TimeSettingServiceImpl extends TimeSettingService {
  var entityDao: EntityDao = _

  def get(project: Project, semester: Semester, campus: Option[Campus]): TimeSetting = {
    var setting: Option[TimeSetting] = None
    campus foreach { c =>
      setting = getCampusSetting(project, semester, c)
    }
    setting match {
      case Some(s) => s
      case None =>
        getGlobalSetting(project, semester) match {
          case Some(s) => s
          case None => throw new RuntimeException("Cannot find suitable timeSetting")
        }
    }
  }

  private def getGlobalSetting(project: Project, semester: Semester): Option[TimeSetting] = {
    val builder = OqlBuilder.from(classOf[TimeSetting], "ts")
    builder.where("ts.project=:project", project)
    builder.where("ts.campus is null")
    builder.where("(ts.endOn is null or :beginOn < ts.endOn) and ts.beginOn <= :endOn", semester.beginOn, semester.endOn)
    builder.cacheable()
    entityDao.search(builder).headOption
  }

  private def getCampusSetting(project: Project, semester: Semester, campus: Campus): Option[TimeSetting] = {
    val builder = OqlBuilder.from(classOf[TimeSetting], "ts")
    builder.where("ts.project=:project", project)
    builder.where("ts.campus=:campus", campus)
    builder.where("(ts.endOn is null or :beginOn < ts.endOn) and ts.beginOn <= :endOn", semester.beginOn, semester.endOn)
    builder.cacheable()
    entityDao.search(builder).headOption
  }
}
