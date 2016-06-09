package org.openurp.edu.course.model

import org.beangle.data.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[Syllabus].on(e => declare(
      e.course & e.locale & e.attachment & e.passed & e.updatedAt & e.beginOn are notnull,
      e.contents is depends("section"),
      e.attachment.name is (notnull, length(50)),
      e.attachment.size is (notnull, column("file_size")),
      e.attachment.key is (notnull, length(200))))

    bind[SyllabusSection].on(e => declare(
      e.title & e.syllabus are notnull,
      e.content is length(3900)))

    bind[SyllabusSectionTitle].on(e => declare(
      e.name is (notnull, length(80))))
  }
}