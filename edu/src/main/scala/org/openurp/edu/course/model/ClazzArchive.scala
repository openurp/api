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

package org.openurp.edu.course.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.model.User
import org.openurp.code.edu.model.ClazzArchiveDoc
import org.openurp.edu.clazz.model.Clazz

/** 课程资料归档
 */
class ClazzArchive extends LongId, Updated {

  /** 文档类型 */
  var doc: ClazzArchiveDoc = _

  /** 教学班 */
  var clazz: Clazz = _

  /** 归档人 */
  var archivedBy: User = _

  /** 文件大小 */
  var docSize: Int = _

  /** 存储路径 */
  var docPath: String = _
}
