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
package org.openurp.edu.extern.service

import org.beangle.data.model.annotation.code
import org.openurp.edu.base.model.Student
import org.openurp.edu.extern.code.model.{ ExamCategory, ExamSubject }
import org.openurp.edu.extern.model.Certificate

trait CertificateService {

  def saveOrUpdate(examGrade: Certificate): Unit

  def getBest(std: Student, category: ExamCategory): Certificate

  def getPassed(std: Student, subjects: Iterable[ExamSubject]): List[Certificate]

  def isPass(std: Student, subject: ExamSubject): Boolean

  def get(std: Student, best: Boolean): Iterable[Certificate]
}
