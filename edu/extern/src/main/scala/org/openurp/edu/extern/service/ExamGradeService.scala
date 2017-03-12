/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
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
package org.openurp.edu.extern.service

import org.openurp.edu.base.model.Student
import org.openurp.edu.extern.exam.model.ExamGrade
import org.openurp.edu.extern.code.model.ExamSubject
import org.openurp.edu.extern.code.model.ExamCategory


trait ExamGradeService {

  def saveOrUpdate(ExamGrade: ExamGrade): Unit

  def getBestGrade(std: Student, category: ExamCategory): ExamGrade

  def getPassGradesOf(std: Student, subjects: Iterable[ExamSubject]): List[ExamGrade]

  def isPass(std: Student, subject: ExamSubject): Boolean

  def getExamGrades(std: Student, best: Boolean): Iterable[ExamGrade]
}
