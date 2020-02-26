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
package org.openurp.edu.course.model

import org.beangle.data.orm.{IdGenerator, MappingModule}

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator(IdGenerator.DateTime)
    defaultCache("openurp.edu.course", "read-write")

    bind[Syllabus].declare { e =>
      e.contents is depends("syllabus")
      e.attachment.name is(notnull, length(50))
      e.attachment.size is(notnull, column("file_size"))
      e.attachment.key is(notnull, length(200))
    }

    bind[SyllabusSection].declare { e =>
      e.title & e.syllabus are notnull
      e.content is length(3900)
    }

    bind[SyllabusSectionTitle].declare { e =>
      e.name is(notnull, length(80))
    }

    bind[CourseBlog] declare { e =>
      e.description is length(40000)
      e.enDescription is length(40000)
    }

    bind[LecturePlan]
    bind[Syllabus]

    bind[TeacherBlog] declare { e =>
      e.intro is length(40000)
      e.harvest is length(40000)
    }
  }
}
