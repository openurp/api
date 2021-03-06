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
import org.openurp.edu.extern.code.model.{CertificateCategory, CertificateSubject}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[CertificateSubject].generator(IdGenerator.Code)
    bind[CertificateCategory].generator(IdGenerator.Code)

    bind[ExchangeSchool].generator(IdGenerator.AutoIncrement)

    bind[CertificateGrade].declare { e =>
      e.scoreText is length(5)
      e.certificate & e.examNo are length(80)
    }

    bind[ExchangeStudent].declare { e =>
      e.grades is depends("exchangeStudent")
    }

    bind[ExchangeGrade].declare { e =>
      e.courseName is length(255)
      e.scoreText is length(10)
    }

    bind[ExemptionCredit]
  }

}
