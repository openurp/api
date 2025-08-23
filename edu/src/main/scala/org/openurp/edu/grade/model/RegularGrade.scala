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
import org.openurp.base.std.model.Student
import org.openurp.edu.clazz.model.Clazz
import org.openurp.edu.grade.model.RegularGrade.{Test, fromJson}

/** 平时总评成绩
 */
@beta
class RegularGrade extends LongId, Updated {

  var clazz: Clazz = _

  var std: Student = _

  var score: Float = _

  var testsJson: JsonArray = new JsonArray

  var status: Int = _

  def tests: Map[String, RegularGrade.Test] = {
    testsJson.map { t =>
      val test = fromJson(t.asInstanceOf[JsonObject])
      (test.name, test)
    }.toMap
  }

  def getTest(name: String): Option[RegularGrade.Test] = {
    findTestJson(name).map(x => RegularGrade.fromJson(x))
  }

  private def findTestJson(name: String): Option[JsonObject] = {
    tests.find { t =>
      val g = t.asInstanceOf[JsonObject]
      g.getString("name") == name
    }.asInstanceOf[Option[JsonObject]]
  }

  def updateTest(name: String, score: Float, percent: Int, details: Option[String] = None): Unit = {
    findTestJson(name) match {
      case None =>
        testsJson.add(Test(name, score, percent, details).toJson)
      case Some(t) =>
        t.add("name", name)
        t.add("score", score)
        t.add("percent", percent)
        t.add("details", details.orNull)
    }
  }

  def removeTest(name: String): Unit = {
    findTestJson(name) foreach { t =>
      testsJson.substractOne(t)
    }
  }

  def changeName(oldName: String, newName: String): Unit = {
    findTestJson(oldName) foreach { t =>
      t.update("name", newName)
    }
  }
}

object RegularGrade {

  def fromJson(g: JsonObject): Test = {
    val name = g.getString("name")
    val score = g.getDouble("score").floatValue
    val percent = g.getInt("percent")
    val details = Option(g.getString("details", null))
    Test(name, score, percent, details)
  }

  case class Test(name: String, score: Float, percent: Int, details: Option[String] = None) {
    def toJson: JsonObject = {
      val j = new JsonObject()
      j.add("name", name)
      j.add("score", score)
      j.add("percent", percent)
      j.add("details", details)
    }
  }
}
