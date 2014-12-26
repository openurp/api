package org.openurp.edu.award.code.model

import org.beangle.data.model.bean.{CodedBean, IntIdBean, NamedBean}
import org.openurp.edu.award.code.{SubsidyCategory, SubsidyLevel}
import org.openurp.edu.award.code.StdHonorLevel

class StdHonorLevelBean extends IntIdBean with CodedBean with NamedBean with StdHonorLevel {
  
  /**个人荣誉种类*/
  var subsidyCategory: SubsidyCategory=_
  
  /**奖励金额*/
  var awardAmount: Float = _
  
   /**使用状态*/
  var enabled: Boolean = _
  
  /**描述*/
   
  var discription: String = _
}