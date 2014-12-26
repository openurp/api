package org.openurp.edu.award.code.model

import org.beangle.data.model.bean.NamedBean
import org.beangle.data.model.bean.CodedBean
import org.beangle.data.model.bean.IntIdBean
import org.openurp.edu.award.code.StipendCategory
import org.openurp.edu.award.code.StipendLevel

class StipendLevelBean extends IntIdBean with CodedBean with NamedBean with StipendLevel {
  
  /**助学金种类*/
  var stipendCategory: StipendCategory=_
  
  /**奖励金额*/
  var awardAmount: Float = _
  
   /**使用状态*/
  var enabled: Boolean = _
  
  /**描述*/
   
  var discription: String = _
}