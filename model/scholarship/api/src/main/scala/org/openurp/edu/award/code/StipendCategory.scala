package org.openurp.edu.award.code

import org.beangle.data.model.IntIdEntity
import java.util.Date

trait StipendCategory extends IntIdEntity {
    /**助学金代码*/
  def code: String 
  
    /**助学金名称*/
  def name: String 
  
//    /**设立日期*/
//  def beginOn: Date 
//  
//   /**废止日期*/
//  def endOn:Date
  
//  /**助学金类型*/
//  def StipendType: StipendType 
  
  /**助学金描述*/
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