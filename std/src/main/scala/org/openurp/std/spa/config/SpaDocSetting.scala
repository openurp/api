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

package org.openurp.std.spa.config

import org.beangle.data.model.IntId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.Enabled
import org.openurp.base.doc.{Orientation, PageSize}
import org.openurp.base.model.Project
import org.openurp.code.std.model.StdDocType

object DocSetting {
  val TranscriptZh = "transcript_zh";
  val TranscriptEn = "transcript_en";

  val StudentZh = "student_zh";
  val StudentEn = "student_en";
}

/** 打印文档的类型
 */
@config
class SpaDocSetting extends IntId, Enabled {

  /** 项目 */
  var project: Project = _

  /** 文档类型 */
  var docType: StdDocType = _

  /** 访问地址 */
  var url: String = _

  /** 可打印的 */
  var printable: Boolean = _

  /** 可下载的 */
  var downloadable: Boolean = _

  /** 下载时是否启用用户密码 */
  var enableUserPassword: Boolean = _

  /** 通知公告 */
  var notice: Option[String] = None

  /** 纵向还是横向，默认纵向 */
  var orientation: Orientation = Orientation.Portrait

  /** 纸张大小 */
  var pageSize: PageSize = PageSize.A4

}
