/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.edu.program.domain

import org.openurp.edu.program.model.CourseGroup

object PlanUtils {

  def getGroupCredits(group: CourseGroup, term: Int): Float = {
    val terms = group.termCredits.replaceAll("^,", "").replaceAll(",$", "").split(",")
    if (term > terms.length || term < 1) { 0f }
    else { terms(term - 1).toFloat }
  }
}
