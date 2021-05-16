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
package org.openurp.spa.doc.model

import org.beangle.data.model.IntId
import org.beangle.data.model.pojo.{Coded, Enabled, Named}

object DocType {
  val TranscriptZh = "transcript_zh";
  val TranscriptEn = "transcript_en";

  val StudentZh = "student_zh";
  val StudentEn = "student_en";
}

/** 打印文档的类型
 */
class DocType extends IntId with Named with Coded with Enabled {

  /** 访问地址 */
  var url: String = _

  /** 可下载的 */
  var downloadable: Boolean = _

  /** 下载时是否启用用户密码 */
  var enableUserPassword: Boolean = _

  /** 管理人员打印地址 */
  var adminUrl: Option[String] = None

  /** 通知公告 */
  var notice: Option[String] = None

  /** 纵向还是横向，默认纵向 */
  var orientation: Orientations.Orientation = Orientations.Portrait

  /** 纸张大小 */
  var pageSize: PageSizes.PageSize = PageSizes.A4

}
