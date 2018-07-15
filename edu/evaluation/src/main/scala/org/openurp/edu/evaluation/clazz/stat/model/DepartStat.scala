/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.evaluation.course.stat.model

import org.beangle.data.model.LongId
import org.openurp.base.model.Department

class DepartEvalStat extends LongId with EvalStat {

  /**部门*/
  var department: Department = _
}
class DepartOptionStat extends LongId with OptionStat

class DepartQuestionStat extends LongId with QuestionStat

class DepartQuestionTypeStat extends LongId with QuestionTypeStat
