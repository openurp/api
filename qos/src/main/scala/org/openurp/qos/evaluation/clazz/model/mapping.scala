/*
 * Copyright (C) 2005, The OpenURP Software.
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
import org.openurp.qos.evaluation.clazz.result.model.{EvaluateResult, QuestionResult}
import org.openurp.qos.evaluation.clazz.stat.model.{ClazzEvalStat, ClazzOptionStat, ClazzQuestionStat, ClazzQuestionTypeStat, CourseEvalStat, CourseOptionStat, CourseQuestionStat, CourseQuestionTypeStat, DepartEvalStat, DepartOptionStat, DepartQuestionStat, DepartQuestionTypeStat, EvalStat, FinalTeacherScore, OptionStat, QuestionStat, QuestionTypeStat, SchoolEvalStat, SchoolOptionStat, SchoolQuestionStat, SchoolQuestionTypeStat, TeacherEvalStat, TeacherOptionStat, TeacherQuestionStat, TeacherQuestionTypeStat}
import org.openurp.qos.evaluation.clazz.result.model._
import org.openurp.qos.evaluation.clazz.stat.model._

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    //clazz result
    bind[EvaluateResult].declare { e =>
      e.clazz & e.student & e.department & e.questionnaire & e.evaluateAt are notnull
      e.questionResults is depends("result")
      e.remark is length(20)
    }
    bind[QuestionResult]
    bind[QuestionnaireClazz]
    bind[FinalTeacherScore]
    bind[EvalStat]
    bind[OptionStat]
    bind[QuestionStat]
    bind[QuestionTypeStat]
    //course stat
    bind[CourseEvalStat].declare { e =>
      e.questionStats is depends(classOf[CourseQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[CourseQuestionTypeStat], "evalStat")
    }
    bind[CourseOptionStat].declare { e =>
      e.questionStat is target[CourseQuestionStat]
    }
    bind[CourseQuestionStat].declare { e =>
      e.evalStat is target[CourseEvalStat]
      e.optionStats is depends(classOf[CourseOptionStat], "questionStat")
    }
    bind[CourseQuestionTypeStat].declare { e =>
      e.evalStat is target[CourseEvalStat]
    }
    //depart stat
    bind[DepartEvalStat].declare { e =>
      e.questionStats is depends(classOf[DepartQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[DepartQuestionTypeStat], "evalStat")
    }
    bind[DepartOptionStat].declare { e =>
      e.questionStat is target[DepartQuestionStat]
    }
    bind[DepartQuestionStat].declare { e =>
      e.evalStat is target[DepartEvalStat]
      e.optionStats is depends(classOf[DepartOptionStat], "questionStat")
    }
    bind[DepartQuestionTypeStat].declare { e =>
      e.evalStat is target[DepartEvalStat]
    }
    // clazz stat
    bind[ClazzEvalStat].declare { e =>
      e.questionStats is depends(classOf[ClazzQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[ClazzQuestionTypeStat], "evalStat")
    }
    bind[ClazzOptionStat].declare { e =>
      e.questionStat is target[ClazzQuestionStat]
    }
    bind[ClazzQuestionStat].declare { e =>
      e.evalStat is target[ClazzEvalStat]
      e.optionStats is depends(classOf[ClazzOptionStat], "questionStat")
    }
    bind[ClazzQuestionTypeStat].declare { e =>
      e.evalStat is target[ClazzEvalStat]
    }
    //school
    bind[SchoolEvalStat].declare { e =>
      e.questionStats is depends(classOf[SchoolQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[SchoolQuestionTypeStat], "evalStat")
    }
    bind[SchoolOptionStat].declare { e =>
      e.questionStat is target[SchoolQuestionStat]
    }
    bind[SchoolQuestionStat].declare { e =>
      e.evalStat is target[SchoolEvalStat]
      e.optionStats is depends(classOf[SchoolOptionStat], "questionStat")
    }
    bind[SchoolQuestionTypeStat].declare { e =>
      e.evalStat is target[SchoolEvalStat]
    }
    //teacher
    bind[TeacherEvalStat].declare { e =>
      e.questionStats is depends(classOf[TeacherQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[TeacherQuestionTypeStat], "evalStat")
    }
    bind[TeacherOptionStat].declare { e =>
      e.questionStat is target[TeacherQuestionStat]
    }
    bind[TeacherQuestionStat].declare { e =>
      e.evalStat is target[TeacherEvalStat]
      e.optionStats is depends(classOf[TeacherOptionStat], "questionStat")
    }
    bind[TeacherQuestionTypeStat].declare { e =>
      e.evalStat is target[TeacherEvalStat]
    }
    bind[TextEvaluation].declare { e =>
      e.teacherRemessages.is(depends("textEvaluation"), table("text_evaluation_remsgs"))
    }
    bind[TeacherRemessage]
  }

}
