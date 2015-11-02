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
package org.openurp.edu.evaluation.model

import scala.reflect.runtime.universe
import org.beangle.data.model.annotation.code
import org.beangle.data.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")
    defaultCache("openurp.edu.evaluation", "read-write")

    bind[EvaluationCriteria].on(e => declare(
      e.name is (length(100), notnull),
      e.criteriaItems is depends("criteria")))

    bind[EvaluationCriteriaItem].on(e => declare(
      e.criteria is notnull))

    bind[Option].on(e => declare(
      e.name is (length(50), notnull),
      e.optionGroup is notnull))

    bind[OptionGroup].on(e => declare(
      e.name is (length(50), notnull),
      e.depart is notnull,
      e.options is depends("optionGroup")))

    bind[Question].on(e => declare(
      e.content is (length(400), notnull),
      e.remark is length(200),
      e.questionType & e.depart & e.optionGroup are notnull))

    bind[Questionnaire].on(e => declare(
      e.depart & e.beginOn are notnull,
      e.remark is length(200),
      e.questions is depends("questionnaire"),
      e.title is length(200),
      e.description is length(500)))

    bind[QuestionType].on(e => declare(
      e.name is (notnull, length(50)),
      e.enName is length(100),
      e.remark is length(100),
      e.beginOn is notnull))

    all.cacheable()
  }

}
