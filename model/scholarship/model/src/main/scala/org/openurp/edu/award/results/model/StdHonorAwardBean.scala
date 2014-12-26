package org.openurp.edu.award.results.model

import org.beangle.data.model.bean.LongIdBean
import org.openurp.teach.core.Student
import org.openurp.base.Semester
import org.openurp.edu.award.code.SubsidyLevel
import org.openurp.edu.award.code.SubsidyCategory
import org.openurp.edu.award.results.StdHonorAward

class StdHonorAwardBean extends LongIdBean with StdHonorAward {
  
  /**个人荣誉种类*/
  var subsidyCategory: SubsidyCategory = _
  
  /**学生*/
  var std: Student = _
  
   /**获奖等级*/
  var subsidyLevel:SubsidyLevel=_
  
  /**评定学期*/
  var semester: Semester = _
  
  /**金额*/
  var awardAmount: Float = _
  
  /**是否审核通过*/
  var BeApproved: Boolean = _
  
}