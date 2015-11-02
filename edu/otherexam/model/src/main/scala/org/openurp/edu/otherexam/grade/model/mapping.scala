/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
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
package org.openurp.edu.otherexam.grade.model

import org.beangle.data.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("date")

    bind[OtherExamGrade].on(e => declare(
      e.std & e.semester & e.subject & e.markStyle & e.examStatus are notnull,
      e.scoreText is length(5),
      e.certificate & e.examNo & e.sessionNo are length(80)))
  }

}
