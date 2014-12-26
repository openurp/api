package org.openurp.edu.award.code

import org.beangle.data.model.IntIdEntity
import java.util.Date

trait StdHonorCategory extends IntIdEntity {
    /**个人荣誉代码*/
  def code: String 
  
    /**个人荣誉名称*/
  def name: String 
  
//    /**设立日期*/
//  def beginOn: Date 
//  
//   /**废止日期*/
//  def endOn:Date
  
//  /**个人荣誉类型*/
//  def StipendType: StipendType 
  
  /**个人荣誉描述*/
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