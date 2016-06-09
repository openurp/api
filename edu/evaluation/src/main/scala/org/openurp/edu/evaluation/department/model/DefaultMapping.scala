package org.openurp.edu.evaluation.department.model

import scala.reflect.runtime.universe
import org.beangle.data.model.bind.Mapping

/**
 * @author xinzhou
 */
class DefaultMapping extends Mapping {

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
