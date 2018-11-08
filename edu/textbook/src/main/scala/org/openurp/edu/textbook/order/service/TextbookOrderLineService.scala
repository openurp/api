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
package org.openurp.edu.textbook.order.service

import org.openurp.edu.base.model.Semester
import org.openurp.edu.base.model.{ Student, Textbook }
import org.openurp.edu.course.model.{ CourseTaker, Clazz }
import org.openurp.edu.textbook.order.model.TextbookOrderLine

trait TextbookOrderLineService {

  def getLessonsHasTextbook(clazzs: Iterable[Clazz]): Set[Long]

  def getTextbooksForLesson(clazz: Clazz): List[Textbook]

  def getTextBookMapByLessons(clazzs: Iterable[Clazz]): Map[Long, List[Textbook]]

  def getLessonsHasOrderTextBook(clazzs: Iterable[Clazz]): Set[Long]

  def getTextbookOrderLinesByLesson(clazzId: Clazz, std: Student): List[TextbookOrderLine]

  def getTextbookOrderLines(std: Student, semester: Semester, clazzId: java.lang.Long): List[TextbookOrderLine]

  def createTextbookOrderLines(clazzId: java.lang.Long,
                               materialNum: Int,
                               semester: Semester,
                               std: Student): List[TextbookOrderLine]

  def createTextbookOrderLines(bookMap: Map[Textbook, Integer], semester: Semester, std: Student): List[TextbookOrderLine]

  def getTextBooks(takes: List[CourseTaker]): Map[Clazz, List[Textbook]]

  def getBookLessons(takes: List[CourseTaker]): Map[Textbook, Clazz]
}
