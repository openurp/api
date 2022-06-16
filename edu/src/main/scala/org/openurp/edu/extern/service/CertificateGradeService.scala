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

package org.openurp.edu.extern.service

import org.openurp.base.std.model.Student
import org.openurp.edu.extern.model.CertificateGrade
import org.openurp.edu.extern.code.CertificateCategory
import org.openurp.edu.extern.code.CertificateSubject

trait CertificateGradeService {

  def getBest(std: Student, category: CertificateCategory): CertificateGrade

  def getPassed(std: Student, subjects: Iterable[CertificateSubject]): List[CertificateGrade]

  def isPass(std: Student, subject: CertificateSubject): Boolean

  def get(std: Student, best: Boolean): Iterable[CertificateGrade]
}
