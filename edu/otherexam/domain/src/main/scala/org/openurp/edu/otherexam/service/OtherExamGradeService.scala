/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.eams.teach.other.service

import org.openurp.edu.base.model.Student
import org.openurp.edu.otherexam.grade.model.OtherExamGrade
import org.openurp.edu.otherexam.code.model.OtherExamSubject
import org.openurp.edu.otherexam.code.model.OtherExamCategory


trait OtherExamGradeService {

  def saveOrUpdate(OtherExamGrade: OtherExamGrade): Unit

  def getBestGrade(std: Student, category: OtherExamCategory): OtherExamGrade

  def getPassGradesOf(std: Student, subjects: Iterable[OtherExamSubject]): List[OtherExamGrade]

  def isPass(std: Student, subject: OtherExamSubject): Boolean

  def getOtherExamGrades(std: Student, best: Boolean): Iterable[OtherExamGrade]
}
