/*
 * Copyright (C) 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openurp.edu.exempt.config

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.InstantRange
import org.openurp.base.edu.code.EducationType
import org.openurp.base.model.{EduLevelBased, Project, Semester}

import scala.collection.mutable

/**
 * 校外考试免修设置
 */
@config
class CertExemptConfig extends LongId with InstantRange with EduLevelBased {

  var semester: Semester = _

  var settings: mutable.Buffer[CertExemptSetting] = new mutable.ArrayBuffer[CertExemptSetting]

  var notice: String = _
}
