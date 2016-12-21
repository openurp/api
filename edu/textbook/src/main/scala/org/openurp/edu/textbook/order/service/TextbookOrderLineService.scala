/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.textbook.order.service

import org.openurp.base.model.Semester
import org.openurp.edu.base.model.{ Student, Textbook }
import org.openurp.edu.lesson.model.{ CourseTaker, Lesson }
import org.openurp.edu.textbook.order.model.TextbookOrderLine

trait TextbookOrderLineService {

  def getLessonsHasTextbook(lessons: Iterable[Lesson]): Set[Long]

  def getTextbooksForLesson(lesson: Lesson): List[Textbook]

  def getTextBookMapByLessons(lessons: Iterable[Lesson]): Map[Long, List[Textbook]]

  def getLessonsHasOrderTextBook(lessons: Iterable[Lesson]): Set[Long]

  def getTextbookOrderLinesByLesson(lessonId: Lesson, std: Student): List[TextbookOrderLine]

  def getTextbookOrderLines(std: Student, semester: Semester, lessonId: java.lang.Long): List[TextbookOrderLine]

  def createTextbookOrderLines(lessonId: java.lang.Long,
                               materialNum: Int,
                               semester: Semester,
                               std: Student): List[TextbookOrderLine]

  def createTextbookOrderLines(bookMap: Map[Textbook, Integer], semester: Semester, std: Student): List[TextbookOrderLine]

  def getTextBooks(takes: List[CourseTaker]): Map[Lesson, List[Textbook]]

  def getBookLessons(takes: List[CourseTaker]): Map[Textbook, Lesson]
}
