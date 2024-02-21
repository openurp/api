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

package org.openurp.std.archive.config

import org.beangle.data.model.IntId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.Enabled
import org.openurp.base.doc.{Orientation, PageSize}
import org.openurp.base.model.Project
import org.openurp.code.std.model.StdDocType

@config
class ArchiveDocSetting extends IntId, Enabled {

  /** 项目 */
  var project: Project = _

  /** 文档类型 */
  var docType: StdDocType = _

  /** 访问地址 */
  var url: String = _

  /** 纵向还是横向，默认纵向 */
  var orientation: Orientation = Orientation.Portrait

  /** 纸张大小 */
  var pageSize: PageSize = PageSize.A4
}
