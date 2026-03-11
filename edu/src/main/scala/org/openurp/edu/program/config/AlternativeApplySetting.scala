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

package org.openurp.edu.program.config

import org.beangle.commons.collection.Properties
import org.beangle.commons.json.{JsonMapper, JsonObject}
import org.beangle.data.model.pojo.InstantRange
import org.openurp.base.std.model.Grade

import java.time.Instant
import java.time.format.DateTimeFormatter

object AlternativeApplySetting extends JsonMapper[AlternativeApplySetting] {

  override def toJson(setting: AlternativeApplySetting): JsonObject = {
    val jo = new JsonObject()
    val isoFormatter = DateTimeFormatter.ISO_INSTANT
    jo.add("beginAt", isoFormatter.format(setting.beginAt))
    jo.add("endAt", isoFormatter.format(setting.endAt))
    val gradeJson = setting.grades.map { g => new JsonObject(new Properties(g, "id", "code", "name")) }
    jo.add("grades", gradeJson)
    jo
  }

  override def fromJson(obj: JsonObject): AlternativeApplySetting = {
    val setting = new AlternativeApplySetting
    setting.beginAt = Instant.parse(obj.getString("beginAt"))
    setting.endAt = Instant.parse(obj.getString("endAt"))
    setting.grades = obj.getArray("grades").map {
      case jo: JsonObject => new Grade(jo.getLong("id"), jo.getString("code"), jo.getString("name"))
    }.toSet
    setting
  }
}

class AlternativeApplySetting extends InstantRange {

  var grades: Set[Grade] = Set.empty

}
