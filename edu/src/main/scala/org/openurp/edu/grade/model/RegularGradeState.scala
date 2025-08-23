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

package org.openurp.edu.grade.model

import org.beangle.commons.json.{JsonArray, JsonObject}
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.edu.clazz.model.Clazz
import org.openurp.edu.grade.model.RegularGradeState.Component

import java.time.Instant

/** 平时成绩状态
 */
@beta
class RegularGradeState extends LongId, Updated {

  /** 教学任务 */
  var clazz: Clazz = _

  /** 各个成绩类型的百分比 */
  var componentsJson: JsonArray = new JsonArray()

  /** 成绩状态 */
  var status: Int = _

  /** 平时成绩优秀率上限 */
  var excellentRateLimit: Float = _

  /** 优秀率 */
  var excellentRate: Float = _

  def this(clazz: Clazz) = {
    this()
    this.clazz = clazz
    this.updatedAt = Instant.now
  }

  def components: Map[String, Component] = {
    componentsJson.map { t =>
      val c = RegularGradeState.fromJson(t.asInstanceOf[JsonObject])
      (c.name, c)
    }.toMap
  }

}

object RegularGradeState {

  def fromJson(g: JsonObject): Component = {
    val id = g.getInt("id")
    val name = g.getString("name")
    val percent = g.getInt("percent")
    Component(id, name, percent)
  }

  case class Component(id: Int, name: String, percent: Int) {
    def toJson: JsonObject = {
      val j = new JsonObject()
      j.add("name", id)
      j.add("name", name)
      j.add("percent", percent)
    }
  }
}
