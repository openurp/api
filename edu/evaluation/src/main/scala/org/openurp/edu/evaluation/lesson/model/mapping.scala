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
package org.openurp.edu.evaluation.lesson.model

import scala.reflect.runtime.universe

import org.beangle.data.orm.MappingModule
import org.openurp.edu.evaluation.lesson.result.model._
import org.openurp.edu.evaluation.lesson.stat.model._

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    defaultIdGenerator("date")

    //lesson result
    bind[EvaluateResult].on(e => declare(
      e.lesson & e.student & e.department & e.questionnaire & e.evaluateAt are notnull,
      e.questionResults is depends("result"),
      e.remark is length(20)))

    bind[QuestionResult].on(e => declare(
      e.result & e.questionType & e.question are notnull))

    bind[QuestionnaireLesson].on(e => declare(
      e.lesson & e.questionnaire are notnull))
    //   finalTeacherScore
    bind[FinalTeacherScore].on(e => declare(
      e.teacher & e.semester are notnull))

    //eval stat
    bind[EvalStat].on(e => declare(
      e.semester & e.statAt & e.questionnaire are notnull))

    bind[OptionStat].on(e => declare(
      e.option is notnull))

    bind[QuestionStat].on(e => declare(
      e.question is notnull))

    bind[QuestionTypeStat].on(e => declare(
      e.questionType is notnull))

    //course stat
    bind[CourseEvalStat].on(e => declare(
      e.course & e.teacher are notnull,
      e.questionStats is depends(classOf[CourseQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[CourseQuestionTypeStat], "evalStat")))

    bind[CourseOptionStat].on(e => declare(
      e.questionStat is (notnull, target[CourseQuestionStat])))

    bind[CourseQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[CourseEvalStat]),
      e.optionStats is depends(classOf[CourseOptionStat], "questionStat")))

    bind[CourseQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[CourseEvalStat])))

    //depart stat
    bind[DepartEvalStat].on(e => declare(
      e.department & e.semester are notnull,
      e.questionStats is depends(classOf[DepartQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[DepartQuestionTypeStat], "evalStat")))

    bind[DepartOptionStat].on(e => declare(
      e.questionStat is (notnull, target[DepartQuestionStat])))

    bind[DepartQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[DepartEvalStat]),
      e.optionStats is depends(classOf[DepartOptionStat], "questionStat")))

    bind[DepartQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[DepartEvalStat])))

    // lesson stat
    bind[LessonEvalStat].on(e => declare(
      e.lesson is notnull,
      e.questionStats is depends(classOf[LessonQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[LessonQuestionTypeStat], "evalStat")))

    bind[LessonOptionStat].on(e => declare(
      e.questionStat is (notnull, target[LessonQuestionStat])))

    bind[LessonQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[LessonEvalStat]),
      e.optionStats is depends(classOf[LessonOptionStat], "questionStat")))

    bind[LessonQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[LessonEvalStat])))

    //school
    bind[SchoolEvalStat].on(e => declare(
      e.questionStats is depends(classOf[SchoolQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[SchoolQuestionTypeStat], "evalStat")))

    bind[SchoolOptionStat].on(e => declare(
      e.questionStat is (notnull, target[SchoolQuestionStat])))

    bind[SchoolQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[SchoolEvalStat]),
      e.optionStats is depends(classOf[SchoolOptionStat], "questionStat")))

    bind[SchoolQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[SchoolEvalStat])))

    //teacher
    bind[TeacherEvalStat].on(e => declare(
      e.teacher is notnull,
      e.questionStats is depends(classOf[TeacherQuestionStat], "evalStat"),
      e.questionTypeStats is depends(classOf[TeacherQuestionTypeStat], "evalStat")))

    bind[TeacherOptionStat].on(e => declare(
      e.questionStat is (notnull, target[TeacherQuestionStat])))

    bind[TeacherQuestionStat].on(e => declare(
      e.evalStat is (notnull, target[TeacherEvalStat]),
      e.optionStats is depends(classOf[TeacherOptionStat], "questionStat")))

    bind[TeacherQuestionTypeStat].on(e => declare(
      e.evalStat is (notnull, target[TeacherEvalStat])))

    bind[TextEvaluation].on(e => declare(
      e.student & e.lesson & e.evaluateByTeacher & e.evaluateAt are notnull,
      e.teacherRemessages is (depends("textEvaluation"), table("text_evaluation_remsgs"))))

    bind[TeacherRemessage].on(e => declare(
      e.textEvaluation & e.visible are notnull))

  }

}
