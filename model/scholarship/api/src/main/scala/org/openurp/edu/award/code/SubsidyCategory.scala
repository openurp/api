package org.openurp.edu.award.code

import org.beangle.data.model.IntIdEntity
import java.util.Date

trait SubsidyCategory extends IntIdEntity {
    /**困难补助代码*/
  def code: String 
  
    /**困难补助名称*/
  def name: String 
  
//    /**设立日期*/
//  def beginOn: Date 
//  
//   /**废止日期*/
//  def endOn:Date
  
//  /**困难补助类型*/
//  def StipendType: StipendType 
  
  /**困难补助描述*/
  def discription: String 
  
  /**评定周期*/
  def period: String 
  
  /**颁奖单位*/
  def awardUnit: String 
  
  /**使用状态*/
  def enabled: Boolean 
  
  /**是否分等级*/
  def beRated: Boolean 
}