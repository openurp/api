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

package org.openurp.std.info.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {

    bind[Examinee].declare { e =>
      e.code is length(30)
      e.examNo is length(30)
      e.letterNo is length(30)
      e.schoolName is length(200)
      index("", true, e.std)
    }

    bind[Home].declare { e =>
      e.formerAddr is length(100)
      e.phone is length(20)
      e.postcode is length(20)
      e.address is length(150)
      e.police is length(150)
      e.policePhone is length(20)

      index("", true, e.std)
    }
    bind[Contact].declare { e =>
      e.email is length(100)
      e.phone is length(20)
      e.mobile is length(50)
      e.address is length(150)
      index("", true, e.std)
    }

    bind[SocialRelation] declare { e =>
      e.duty is length(400)
      index("", false, e.std)
    }

    bind[MajorStudent] declare { e =>
      index("", true, e.std)
    }

    bind[EduWorkRecord] declare { e =>
      e.duty is length(100)
      e.remark is length(100)
      e.organization is length(200)
    }

    bind[Freshman] declare { e =>
      e.code is length(20)
      e.idCode is length(20)
      e.name is length(60)
      e.dormitoryNo is length(20)
      e.homeTown & e.birthplace are length(100)
      e.organization is length(100)
      e.phone is length(20)
      e.email is length(100)
    }

    bind[Foreigner] declare { e =>
      e.cscNo & e.visaNo & e.residenceNo & e.passportNo are length(20)
    }

    bind[PersonCheckItem] declare { e =>
      e.oldValue & e.newValue is length(200)
    }

    bind[PersonCheck] declare { e =>
      e.changes is depends("check")
      e.auditOpinion is length(100)
    }
  }
}
