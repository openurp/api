/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2017, OpenURP Software.
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
package org.openurp.people.base.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{ TemporalOn, Updated }
import org.openurp.code.person.model.PoliticalStatus

/**
 * 政治面貌
 */
class PoliticalState extends LongId with Updated with TemporalOn {

  /**人员*/
  var person: Person = _

  /**政治面貌 */
  var status: PoliticalStatus = _

}