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
package org.openurp.edu.evaluation.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultCache("openurp.edu.evaluation", "read-write")

    bind[EvaluationCriteria].declare { e =>
      e.name is(length(100), notnull)
      e.criteriaItems is depends("criteria")
    }
    bind[EvaluationCriteriaItem].declare { e =>
      e.criteria is notnull
    }
    bind[Option].declare { e =>
      e.name is(length(50), notnull)
      e.optionGroup is notnull
    }
    bind[OptionGroup].declare { e =>
      e.name is(length(50), notnull)
      e.options is depends("optionGroup")
    }
    bind[Question].declare { e =>
      e.contents is(length(400), notnull)
      e.remark is length(200)
      e.questionType & e.depart are notnull
    }
    bind[Questionnaire].declare { e =>
      e.depart & e.beginOn are notnull
      e.remark is length(200)
      e.title is length(200)
      e.description is length(500)
    }
    bind[QuestionType].declare { e =>
      e.name is(notnull, length(50))
      e.enName is length(100)
      e.remark is length(100)
      e.beginOn is notnull
    }

    all.cacheable()
  }

}
