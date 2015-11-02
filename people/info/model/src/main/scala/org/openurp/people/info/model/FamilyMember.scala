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

import org.beangle.data.model.{ Coded, LongId, Named }
import org.openurp.code.geo.model.Country
import org.openurp.code.job.model.ProfessionalTitle
import org.openurp.code.person.model.{ FamilyRelationship, HealthStatus, IdType, JobStatus, Nation, PoliticalStatus }
import org.openurp.people.base.model.Person

class FamilyMember extends LongId with Coded with Named {
  var person: Person = _
  /**亲属关系 */
  var familyRelationship: FamilyRelationship = _

  var enName: String = _

  var birthday: java.sql.Date = _
  /**民族 */
  var nation: Nation = _
  /**国籍/地区 */
  var country: Country = _
  /**健康状况 */
  var healthStatus: HealthStatus = _
  var workPlace: String = _
  /**从业状况 */
  var jobStatus: JobStatus = _

  var email: String = _
  var mobile: String = _

  /**专业技术职务*/
  var techPosition: ProfessionalTitle = _

  var idType: IdType = _
  var sid: String = _

  var politicalStatus: PoliticalStatus = _

  var job: String = _
  /**年收入 */
  var annualIncome: Float = _
}