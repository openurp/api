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

package org.openurp.edu.extern.model

import org.beangle.data.orm.MappingModule

object DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[CertSignup].declare { e =>
      e.examNo is length(100)
      e.examRoom is length(100)
      index("", true, e.std, e.semester, e.certificate)
    }

    bind[CertificateGrade].declare { e =>
      e.scoreText is length(10)
      e.subject is length(80)
      e.certificateNo & e.examNo are length(80)

      index("", true, e.std, e.certificate, e.acquiredIn)
      index("", false, e.semester)
    }
    bind[ExternGrade] declare { e =>
      e.courseName is length(400)
      e.scoreText is length(20)
    }
  }
}
