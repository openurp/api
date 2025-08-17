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

package org.openurp.edu.his.model

import org.beangle.data.orm.{IdGenerator, MappingModule}
import org.openurp.base.model.ArchivedByYear

class DefaultMapping extends MappingModule {

  def binding(): Unit = {

    defaultIdGenerator(classOf[Long], IdGenerator.Assigned)

    bind[ArchivedByYear].declare { e =>
      e.schoolYear is partitionKey
    }

    bind[HisCourseGrade].declare { e =>
      e.crn is length(20)
      e.course & e.courseTakeType & e.project & e.courseType & e.semester & e.gradingMode are notnull
      e.operator is length(100)
      e.scoreText is length(10)
      e.remark is length(200)
      e.provider is length(80)
      e.examGrades & e.gaGrades are depends("courseGrade")

      index("", false, e.std)
      index("", false, e.clazz)
      index("", false, e.project)
    }

    bind[HisExamGrade].declare { e =>
      e.scoreText is length(10)
    }

    bind[HisGaGrade].declare { e =>
      e.scoreText is length(10)
    }

    bind[HisRegularGrade].declare { e =>
      e.tests is depends("regularGrade")

      index("", false, e.std)
      index("", false, e.clazz)
    }

    bind[HisRegularTestGrade].declare { e =>
      e.details is length(100)
    }

    bind[HisCourseTaker].declare { e =>
      e.remark is length(100)
      index("", false, e.clazz)
      index("", false, e.semester)
    }

    bind[HisExamTaker] declare { e =>
      index("", false, e.clazz)
      index("", false, e.examRoom)
    }

    bind[HisCourseProfile]
  }

}
