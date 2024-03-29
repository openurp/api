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

package org.openurp.base.service

object Feature {
  val IntType = "integer"
  val FloatType = "float"
  val StringType = "string"
  val BooleanType = "boolean"
  val JsonType = "json"
  val types = Set(IntType, FloatType, StringType, BooleanType, JsonType)

  def convert(value: String, typeName: String): Any = {
    val c = typeName match
      case Feature.BooleanType => value.toBoolean
      case Feature.FloatType => value.toFloat
      case Feature.IntType => value.toInt
      case _ => value
    c
  }

  def apply(scope: FeatureScope, name: String, description: String, defaultValue: Any): Feature = {
    apply(scope, name, description, defaultValue, List.empty)
  }

  def apply(scope: FeatureScope, name: String, description: String, options: Seq[Any]): Feature = {
    assert(options.nonEmpty)
    apply(scope, name, description, options.head, options)
  }

  def apply(scope: FeatureScope, name: String, description: String, defaultValue: Any, options: Seq[Any]): Feature = {
    val list = options.map(_.toString)
    val globalName = scope.namespace + "." + name
    defaultValue match {
      case i: Int => Feature(globalName, description, IntType, i.toString, list)
      case f: Float => Feature(globalName, description, FloatType, f.toString, list)
      case b: Boolean => Feature(globalName, description, BooleanType, b.toString, list)
      case _ => Feature(globalName, description, StringType, defaultValue.toString, list)
    }
  }

  def apply(scope: FeatureScope, name: String, description: String, typeName: String, defaultValue: String): Feature = {
    assert(types.contains(typeName), "given typeName should be one of integer/float/string/boolean")
    val globalName = scope.namespace + "." + name
    Feature(globalName, description, typeName, defaultValue, List.empty)
  }
}

case class Feature(name: String, description: String, typeName: String, defaultValue: String, options: Seq[String]) {

}

trait FeatureScope {
  def namespace: String
}
