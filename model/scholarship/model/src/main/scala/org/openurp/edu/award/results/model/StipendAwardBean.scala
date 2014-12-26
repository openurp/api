package org.openurp.edu.award.results.model

import org.beangle.data.model.bean.LongIdBean
import org.openurp.teach.core.Student
import org.openurp.base.Semester
import org.openurp.edu.award.code.StipendLevel
import org.openurp.edu.award.results.StipendAward
import org.openurp.edu.award.code.StipendCategory

class StipendAwardBean extends LongIdBean with StipendAward {
  
  /**助学金种类*/
  var stipendCategory: StipendCategory = _
  
  /**学生*/
  var std: Student = _
  
   /**获奖等级*/
  var stipendLevel:StipendLevel=_
  
  /**评定学期*/
  var semester: Semester = _
  
  /**金额*/
  var awardAmount: Float = _
  
  /**是否审核通过*/
  var BeApproved: Boolean = _
  
}