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
package org.openurp.edu.base.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.code
import org.beangle.data.model.pojo.Named
import org.beangle.data.model.pojo.Remark
import org.beangle.data.model.pojo.TemporalOn
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.Campus
import org.openurp.base.model.Department
import org.openurp.code.edu.model.Degree
import org.openurp.code.edu.model.StudyType
import org.openurp.edu.base.EduLevelBased
import org.openurp.edu.base.States
import org.openurp.edu.base.code.model.StdType
import org.openurp.edu.base.code.model.CourseType

/**
 * 专业培养方案
 *
 * @author chaostone
 *
 */
class Program extends LongId with Updated with Named with Cloneable with TemporalOn with EduLevelBased with Remark {

	/** 年级 */
	var grade: String = _

	/** 部门 */
	var department: Option[Department] = None

	/** 学生类别 */
	var stdType: Option[StdType] = None

	var camups: Option[Campus] = None

	/** 专业  */
	var major: Major = _

	/** 专业方向 */
	var direction: Option[Direction] = None

	/** 学制  */
	var duration: Float = _

	/** 学习形式 */
	var studyType: StudyType = _

	/** 毕业授予学位 */
	var degree: Option[Degree] = None

	/** 学位绩点 */
	var degreeGpa: Option[Float] = None

	/** 学期对应校区 */
	var termCampuses = Collections.newBuffer[TermCampus]

	/** 审核状态 */
	var state: States.State = States.Draft

	def campuses: Set[Campus] = {
		termCampuses.map(_.campus).toSet
	}

	/** 多出学分可以冲抵的课程类别 */
	var offsetType: CourseType = _
}
