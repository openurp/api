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

import org.openurp.base.model.{Project, Semester}

import java.time.LocalDate

trait SemesterService {

  def getActives(project: Project): Seq[Semester]

  def get(project: Project, date: LocalDate): Semester

  def get(project: Project, beginOn: LocalDate, endOn: LocalDate): (Seq[Semester], Seq[Semester])

  /**
   * get semester by index
   *
   * @param project
   * @param beginOn
   * @param endOn
   * @param index start with 1
   */
  def get(project: Project, beginOn: LocalDate, endOn: LocalDate, index: Int): Option[Semester]
}
