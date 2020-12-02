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
package org.openurp.edu.evaluation.clazz.model

import org.beangle.data.orm.MappingModule
import org.openurp.edu.evaluation.clazz.result.model._
import org.openurp.edu.evaluation.clazz.stat.model._

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    //clazz result
    bind[EvaluateResult].declare { e =>
      e.clazz & e.student & e.department & e.questionnaire & e.evaluateAt are notnull
      e.questionResults is depends("result")
      e.remark is length(20)
    }
    bind[QuestionResult].declare { e =>
      e.result & e.questionType & e.question are notnull
    }
    bind[QuestionnaireClazz].declare { e =>
      e.clazz & e.questionnaire are notnull
    } //   finalTeacherScore
    bind[FinalTeacherScore].declare { e =>
      e.teacher & e.semester are notnull
    }
    //eval stat
    bind[EvalStat].declare { e =>
      e.semester & e.statAt & e.questionnaire are notnull
    }
    bind[OptionStat].declare { e =>
      e.option is notnull
    }
    bind[QuestionStat].declare { e =>
      e.question is notnull
    }
    bind[QuestionTypeStat].declare { e =>
      e.questionType is notnull
    }
    //course stat
    bind[CourseEvalStat].declare { e =>
      e.course & e.teacher are notnull
      e.questionStats is depends(classOf[CourseQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[CourseQuestionTypeStat], "evalStat")
    }
    bind[CourseOptionStat].declare { e =>
      e.questionStat is(notnull, target[CourseQuestionStat])
    }
    bind[CourseQuestionStat].declare { e =>
      e.evalStat is(notnull, target[CourseEvalStat])
      e.optionStats is depends(classOf[CourseOptionStat], "questionStat")
    }
    bind[CourseQuestionTypeStat].declare { e =>
      e.evalStat is(notnull, target[CourseEvalStat])
    }
    //depart stat
    bind[DepartEvalStat].declare { e =>
      e.department & e.semester are notnull
      e.questionStats is depends(classOf[DepartQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[DepartQuestionTypeStat], "evalStat")
    }
    bind[DepartOptionStat].declare { e =>
      e.questionStat is(notnull, target[DepartQuestionStat])
    }
    bind[DepartQuestionStat].declare { e =>
      e.evalStat is(notnull, target[DepartEvalStat])
      e.optionStats is depends(classOf[DepartOptionStat], "questionStat")
    }
    bind[DepartQuestionTypeStat].declare { e =>
      e.evalStat.is(notnull, target[DepartEvalStat])
    }
    // clazz stat
    bind[ClazzEvalStat].declare { e =>
      e.clazz is notnull
      e.questionStats is depends(classOf[ClazzQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[ClazzQuestionTypeStat], "evalStat")
    }
    bind[ClazzOptionStat].declare { e =>
      e.questionStat is(notnull, target[ClazzQuestionStat])
    }
    bind[ClazzQuestionStat].declare { e =>
      e.evalStat is(notnull, target[ClazzEvalStat])
      e.optionStats is depends(classOf[ClazzOptionStat], "questionStat")
    }
    bind[ClazzQuestionTypeStat].declare { e =>
      e.evalStat is(notnull, target[ClazzEvalStat])
    }
    //school
    bind[SchoolEvalStat].declare { e =>
      e.questionStats is depends(classOf[SchoolQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[SchoolQuestionTypeStat], "evalStat")
    }
    bind[SchoolOptionStat].declare { e =>
      e.questionStat is(notnull, target[SchoolQuestionStat])
    }
    bind[SchoolQuestionStat].declare { e =>
      e.evalStat is(notnull, target[SchoolEvalStat])
      e.optionStats is depends(classOf[SchoolOptionStat], "questionStat")
    }
    bind[SchoolQuestionTypeStat].declare { e =>
      e.evalStat.is(notnull, target[SchoolEvalStat])
    }
    //teacher
    bind[TeacherEvalStat].declare { e =>
      e.teacher is notnull
      e.questionStats is depends(classOf[TeacherQuestionStat], "evalStat")
      e.questionTypeStats is depends(classOf[TeacherQuestionTypeStat], "evalStat")
    }
    bind[TeacherOptionStat].declare { e =>
      e.questionStat.is(notnull, target[TeacherQuestionStat])
    }
    bind[TeacherQuestionStat].declare { e =>
      e.evalStat.is(notnull, target[TeacherEvalStat])
      e.optionStats is depends(classOf[TeacherOptionStat], "questionStat")
    }
    bind[TeacherQuestionTypeStat].declare { e =>
      e.evalStat.is(notnull, target[TeacherEvalStat])
    }
    bind[TextEvaluation].declare { e =>
      e.student & e.clazz & e.evaluateByTeacher & e.evaluateAt are notnull
      e.teacherRemessages.is(depends("textEvaluation"), table("text_evaluation_remsgs"))
    }
    bind[TeacherRemessage].declare { e =>
      e.textEvaluation & e.visible are notnull
    }
  }

}
