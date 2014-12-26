package org.openurp.edu.award.results.model

import org.beangle.data.model.bean.LongIdBean
import org.openurp.teach.core.Student
import org.openurp.base.Semester
import org.openurp.edu.award.code.ScholarshipLevel
import org.openurp.edu.award.results.ScholarshipAward
import org.openurp.edu.award.code.ScholarshipCategory

class ScholarshipAwardBean extends LongIdBean with ScholarshipAward {
  
  /**奖学金种类*/
  var scholarshipCategory: ScholarshipCategory = _
  
  /**学生*/
  var std: Student = _
  
   /**获奖等级*/
  var scholarshipLevel:ScholarshipLevel=_
  
  /**评定学期*/
  var semester: Semester = _
  
  /**金额*/
  var awardAmount: Float = _
  
  /**是否审核通过*/
  var BeApproved: Boolean = _
  
}