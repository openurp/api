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

package org.openurp.edu.textbook.service

import org.openurp.base.edu.model.Textbook
import org.openurp.base.model.Semester
import org.openurp.base.std.model.Student
import org.openurp.edu.clazz.model.{Clazz, CourseTaker}
import org.openurp.edu.textbook.model.StdBookOrder

trait StdBookOrderService {

  def getClazzesHasTextbook(clazzs: Iterable[Clazz]): Set[Long]

  def getTextbooksForLesson(clazz: Clazz): List[Textbook]

  def getTextBookMapByLessons(clazzs: Iterable[Clazz]): Map[Long, List[Textbook]]

  def getClazzesHasOrderTextBook(clazzs: Iterable[Clazz]): Set[Long]

  def getStdBookOrdersByLesson(clazzId: Clazz, std: Student): List[StdBookOrder]

  def getStdBookOrders(std: Student, semester: Semester, clazzId: java.lang.Long): List[StdBookOrder]

  def createStdBookOrders(clazzId: java.lang.Long, materialNum: Int, semester: Semester, std: Student): List[StdBookOrder]

  def createStdBookOrders(bookMap: Map[Textbook, Integer], semester: Semester, std: Student): List[StdBookOrder]

  def getTextBooks(takes: List[CourseTaker]): Map[Clazz, List[Textbook]]

  def getBookClazzes(takes: List[CourseTaker]): Map[Textbook, Clazz]
}
