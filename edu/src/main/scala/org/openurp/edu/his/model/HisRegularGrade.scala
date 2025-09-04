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

package org.openurp.edu.his.model

import org.beangle.commons.json.{JsonArray, JsonObject}
import org.beangle.commons.lang.annotation.beta
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.archive
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.ArchivedByYear
import org.openurp.base.std.model.Student
import org.openurp.edu.clazz.model.Clazz
import org.openurp.edu.grade.model.RegularGrade

/** 归档平时成绩
 */
@archive
@beta
class HisRegularGrade extends LongId, Updated, ArchivedByYear {
  /** 教学班 */
  var clazz: Clazz = _
  /** 学生 */
  var std: Student = _
  /** 分数 */
  var score: Float = _
  /** 测试成绩 */
  var testsJson: JsonArray = new JsonArray

  def tests: Map[String, RegularGrade.Test] = {
    testsJson.map { t =>
      val test = RegularGrade.fromJson(t.asInstanceOf[JsonObject])
      (test.name, test)
    }.toMap
  }

  def getTest(name: String): Option[RegularGrade.Test] = {
    findTestJson(name).map(x => RegularGrade.fromJson(x))
  }

  private def findTestJson(name: String): Option[JsonObject] = {
    testsJson.find { t =>
      val g = t.asInstanceOf[JsonObject]
      g.getString("name") == name
    }.asInstanceOf[Option[JsonObject]]
  }

}
