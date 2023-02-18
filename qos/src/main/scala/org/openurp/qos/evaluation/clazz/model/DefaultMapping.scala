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

package org.openurp.qos.evaluation.clazz.model

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    //clazz result
    bind[EvaluateResult].declare { e =>
      e.clazz & e.student & e.department & e.questionnaire are notnull
      e.questionResults is depends("result")
    }
    bind[QuestionResult]
    bind[QuestionnaireClazz]
    bind[FinalTeacherScore]
    //course stat
    bind[CourseEvalStat].declare { e =>
      e.questionStats is depends(classOf[CourseQuestionStat], "stat")
      e.indicatorStats is depends(classOf[CourseIndicatorStat], "stat")
    }
    bind[CourseOptionStat].declare { e =>
      e.questionStat is target[CourseQuestionStat]
    }
    bind[CourseQuestionStat].declare { e =>
      e.stat is target[CourseEvalStat]
      e.optionStats is depends(classOf[CourseOptionStat], "questionStat")
    }
    bind[CourseIndicatorStat].declare { e =>
      e.stat is target[CourseEvalStat]
    }
    //category
    bind[CategoryEvalStat].declare { e =>
      e.ranges is depends("stat")
      e.grades is depends("stat")
    }
    bind[CategoryStatRange]
    bind[CategoryStatGrade]

    bind[Feedback].declare { e =>
      e.contents is length(500)
    }
    bind[FinalComment].declare { e =>
      e.contents is length(500)
    }

    bind[DepartEvalStat]
    bind[SchoolEvalStat]
  }

}
