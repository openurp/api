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

package org.openurp.edu.miniclazz.config

import org.beangle.commons.json.{JsonMapper, JsonObject}
import org.beangle.data.model.pojo.InstantRange

import java.time.Instant
import java.time.format.DateTimeFormatter

object MiniClazzSetting extends JsonMapper[MiniClazzSetting] {

  override def toJson(setting: MiniClazzSetting): JsonObject = {
    val jo = new JsonObject()
    val isoFormatter = DateTimeFormatter.ISO_INSTANT
    setting.schedule foreach { schedule =>
      val s = new JsonObject()
      s.add("beginAt", isoFormatter.format(schedule.beginAt))
      s.add("endAt", isoFormatter.format(schedule.endAt))
      jo.add("schedule", s)
    }
    setting.gradeEntry foreach { entry =>
      val s = new JsonObject()
      s.add("beginAt", isoFormatter.format(entry.beginAt))
      s.add("endAt", isoFormatter.format(entry.endAt))
      jo.add("gradeEntry", s)
    }
    jo
  }

  override def fromJson(jo: JsonObject): MiniClazzSetting = {
    val setting = new MiniClazzSetting
    val schedule = jo.getObject("schedule")
    if (null != schedule && schedule.contains("beginAt")) {
      val s = new SimpleInstantRange
      s.beginAt = Instant.parse(schedule.getString("beginAt"))
      s.endAt = Instant.parse(schedule.getString("endAt"))
      setting.schedule = Some(s)
    }
    val gradeEntry = jo.getObject("gradeEntry")
    if (null != gradeEntry && gradeEntry.contains("beginAt")) {
      val s = new SimpleInstantRange
      s.beginAt = Instant.parse(gradeEntry.getString("beginAt"))
      s.endAt = Instant.parse(gradeEntry.getString("endAt"))
      setting.gradeEntry = Some(s)
    }
    setting
  }
}

/**
 * 专业主课的时间范围
 */
class MiniClazzSetting {
  /** 排课起始和结束时间 */
  var schedule: Option[SimpleInstantRange] = None

  /** 成绩录入起始和结束时间 */
  var gradeEntry: Option[SimpleInstantRange] = None
}

class SimpleInstantRange extends InstantRange
