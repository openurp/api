/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.evaluation.department.model

import scala.reflect.runtime.universe
import org.beangle.data.orm.MappingModule

/**
 * @author xinzhou
 */
class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    //lesson result
    bind[DepartEvaluate].on(e => declare(
      e.department & e.questionnaire & e.evaluateAt & e.semester are notnull,
      e.questionResults is depends("result"),
      e.remark is length(20)))

    bind[DepartQuestion].on(e => declare(
      e.result & e.question & e.score are notnull))

    bind[SupervisiorEvaluate].on(e => declare(
      e.department & e.questionnaire & e.evaluateAt & e.semester are notnull,
      e.questionResults is depends("result"),
      e.remark is length(20)))

    bind[SupervisiorQuestion].on(e => declare(
      e.result & e.question & e.score are notnull))

  }

}
