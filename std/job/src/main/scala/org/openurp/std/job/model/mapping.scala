/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.std.job.model

import org.beangle.commons.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("date")

    bind[EmploymentStatus].on(e => declare(
      e.code is (notnull, length(40)),
      e.name is (notnull, length(80))))

    bind[Graduate].on(e => declare(
      e.stdSource is length(100),
      e.graduateBatch & e.std are notnull))
      
    bind[GraduateBatch].on(e => declare(
      e.code is (notnull, length(40)),
      e.name is (notnull, length(80))))

    bind[StdEmployment].on(e => declare(
      e.employmentStatus & e.std are notnull))

  }

}
