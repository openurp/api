package org.openurp.edu.award.code

import org.beangle.data.model.IntIdEntity

trait StdHonorType extends IntIdEntity {
  /**个人荣誉类型代码 */
  def code: String
  
  /**个人荣誉类型名称 */
  def name: String
  
  /**使用状态*/
  def enabled: Boolean 
  
  /**排序序号*/
  def sequence: String 
}