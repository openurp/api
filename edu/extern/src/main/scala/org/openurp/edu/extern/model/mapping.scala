/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.extern.model

import org.beangle.data.orm.{IdGenerator, MappingModule}
import org.openurp.edu.extern.code.model.{ExamCategory, ExamSubject}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("date")

    bind[ExamSubject].generator(IdGenerator.Code)
    bind[ExamCategory].generator(IdGenerator.Code)

    bind[ExternSchool]
    bind[ExternGrade]

    bind[ExternExamGrade].declare { e =>
      e.scoreText is length(5)
      e.certificate & e.examNo are length(80)
    }
  }

}
