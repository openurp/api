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
package org.openurp.people.info.model

import org.beangle.data.model.LongId
import org.beangle.data.model.TemporalOn
import org.openurp.code.edu.model.DisciplineCategory
import org.openurp.people.base.model.Person
import org.openurp.code.edu.model.Degree
import org.openurp.code.edu.model.EducationDegree
import org.openurp.code.edu.model.StudyType

/**
 * 学历信息
 */
class StudyHistory extends LongId  with TemporalOn {

  /**
   * 学历
   */
	var educationDegree: EducationDegree = _
  
  var person: Person = _
  
  var degree: Degree = _
  var disciplineCategory: DisciplineCategory = _
  var studyType: StudyType = _
  var major: String = _
  var direction: String = _

  var duration: Float = _
  var graduateFrom: String = _
  var degreeAwardBy: String = _

  /**是否第一学历 */
  var first: Boolean = _
  /**证明人*/
  var witness: String = _
  /**学生内容 */
  var content: String = _
  var remark: String = _
}