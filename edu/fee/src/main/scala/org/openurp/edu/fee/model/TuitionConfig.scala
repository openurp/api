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
package org.openurp.edu.fee.model

import org.beangle.data.model.IntId
import org.openurp.base.model.Department
import org.openurp.code.edu.model.EducationLevel
import org.openurp.edu.base.model.Major

/**
 * 收费缺省值
 */
class TuitionConfig extends IntId {

	/** 起始年级 */
	var fromGrade: String = _

	/** 截止年级 */
	var toGrade: String = _

	/** 学历层次 */
	var level: EducationLevel = _

	/** 系 */
	var department: Option[Department] = None

	/** 所属的专业 */
	var major: Option[Major] = None

	/** 收费类型 */
	var feeType: FeeType = _

	/** 对应的值 */
	var amount: Int = _

	/** remark */
	var remark: Option[String] = None

}
