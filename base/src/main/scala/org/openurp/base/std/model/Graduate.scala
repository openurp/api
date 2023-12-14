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

package org.openurp.base.std.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.code.edu.model.{Degree, EducationResult}
import org.openurp.code.std.model.WheretoGo

import java.time.LocalDate

/** 毕业生信息
 *
 */
class Graduate extends LongId, Updated {

  /** 界别 */
  var season: GraduateSeason = _

  /** 学籍 */
  var std: Student = _

  /** 学历证书编号（电子注册号） */
  var certificateNo: Option[String] = None

  /** 毕结业证书序列号 */
  var certificateSeqNo: Option[String] = None

  /** 毕结业日期 */
  var graduateOn: LocalDate = _

  /** 毕结业情况 */
  var result: EducationResult = _

  /** 学位 */
  var degree: Option[Degree] = None

  /** 学位授予日期 */
  var degreeAwardOn: Option[LocalDate] = None

  /** 学位证书编号 */
  var diplomaNo: Option[String] = None

  /** 外语通过年月 */
  var foreignLangPassedOn: Option[LocalDate] = None

  /** 毕业去向 */
  var wheretoGo: Option[WheretoGo] = None

  /** 毕业批次 */
  var batchNo: Int = _
}
