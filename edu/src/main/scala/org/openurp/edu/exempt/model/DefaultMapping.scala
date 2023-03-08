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

package org.openurp.edu.exempt.model

import org.beangle.data.orm.{IdGenerator, MappingModule}
import org.openurp.edu.exempt.model.{CertExemptApply, ExchExemptApply, ExchExemptCredit}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[CertExemptApply] declare { e =>
      e.certificate is length(80)
      e.attachmentPath is length(100)
      e.reasons is length(500)
    }
    bind[ExchExemptApply].declare { e =>
      index("", true, e.externStudent)
    }
    bind[ExchExemptCredit]
  }
}
