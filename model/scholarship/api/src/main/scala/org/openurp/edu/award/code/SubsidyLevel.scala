package org.openurp.edu.award.code

import org.beangle.data.model.IntIdEntity

trait SubsidyLevel extends IntIdEntity {

    /**等级代码*/
  def code: String
  
    /**等级名称*/
  def name: String
  
   /**困难补助种类*/
  def subsidyCategory: SubsidyCategory
  
  /**奖励金额*/
  def awardAmount: Float
  
   /**使用状态*/
  def enabled: Boolean 
  
  
  /**等级描述*/  
  def discription: String
}