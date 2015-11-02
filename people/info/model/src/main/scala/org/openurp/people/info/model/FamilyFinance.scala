/*
 * OpenURP, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.people.info.model

import org.beangle.data.model.LongId
import org.openurp.code.person.model.{ DifficultyCause, DifficultyDegree, FamilyCategory }
import org.openurp.people.base.model.Person

class FamilyFinance extends LongId {

  var person: Person = _
  /**家庭人口 */
  var totalNum: Integer = _
  /**劳动力人口 */
  var laborNum: Integer = _
  /**赡养人口*/
  var supportedNum: Integer = _

  /**家庭类别 */
  var familyCategory: FamilyCategory = _
  /**困难原因 */
  var difficultyCause: DifficultyCause = _
  /**困难程度 */
  var difficultyDegree: DifficultyDegree = _
  /**是否低保 */
  var miniSecurity: Boolean = _
  /**家庭人均月收入 */
  var monthlyIncome: Float = _
  /**就学地低保线*/
  var minimumSecurity: Float = _
  /**家庭主要收入来源*/
  var incomeSource: String = _
}