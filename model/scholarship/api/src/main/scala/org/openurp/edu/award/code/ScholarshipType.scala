package org.openurp.edu.award.code

import org.beangle.data.model.IntIdEntity

trait ScholarshipType extends IntIdEntity {
  /**奖学金类型代码 */
  def code: String
  
  /**奖学金类型名称 */
  def name: String
  
  /**使用状态*/
  def enabled: Boolean 
  
  /**排序序号*/
  def sequence: String 
}