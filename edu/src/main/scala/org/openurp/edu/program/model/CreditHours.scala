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

package org.openurp.edu.program.model

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.Strings
import org.openurp.code.edu.model.TeachingNature

object CreditHours {

  def toHours(creditHours: Int, hourRatios: String, natures: collection.Seq[TeachingNature], practical: Boolean = false):
  Map[TeachingNature, Int] = {
    val natureMap = natures.map(x => (x.id.toString, x)).toMap

    val ratioMap = toRatioMap(hourRatios, natures)
    if (ratioMap.isEmpty) {
      val targetNature =
        if (practical) natures.find(_.id == TeachingNature.Practice).get else natures.find(_.id == TeachingNature.Theory).get
      Map(targetNature -> creditHours)
    } else {
      val sum = ratioMap.values.sum
      if sum == creditHours then ratioMap
      else ratioMap.map(e => (e._1, ((e._2 * 1.0 / sum) * creditHours).toInt))
    }
  }

  def toRatios(hours: collection.Map[TeachingNature, Int]): String = {
    hours.map(x => s"${x._1.id}:${x._2}").toSeq.sorted.mkString(",")
  }

  private def toRatioMap(hourRatios: String, natures: collection.Seq[TeachingNature]): Map[TeachingNature, Int] = {
    if Strings.isBlank(hourRatios) then Map.empty
    else
      val natureMap = natures.map(x => (x.id.toString, x)).toMap
      val ratioMap = Collections.newMap[TeachingNature, Int]
      for (r <- Strings.split(hourRatios, ",")) {
        val natureId = Strings.substringBefore(r, ":")
        val value = Strings.substringAfter(r, ":").toInt
        natureMap.get(natureId) foreach { n =>
          ratioMap.put(n, value)
        }
      }
      ratioMap.toMap
  }
}
