/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.clazz.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.edu.base.code.model.CourseAbilityRate
import org.openurp.edu.base.model.Student

/*** 学生课程能力等级
 */
class StdCourseAbility extends LongId with Updated{

  /**学生*/
  var std:Student=_

  /**分级分数*/
  var score:Option[Float]=None

  /**分级名称*/
  var rate:CourseAbilityRate=_

}