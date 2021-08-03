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
package org.openurp.rd.achievement.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Named, Updated}

import java.time.YearMonth
import scala.collection.mutable

/** 教学成果
 * <p> 科类代码
 * ．成果科类代码组成形式为：abcd，其中：
 * ab：成果所属科类代码：填写科类代码一般应按成果所属学科代码填写。哲学—01，经济学—02，法学—03，教育学—04，文学—05，历史
 * 学—06，理学—07，工学—08，农学—09，医学—10，军事学—11,管理
 * 学—12，艺术学－13，其他—14。
 * c：成果属普通教育填 1，继续教育填 2，其他填 0。
 * d：成果属本科教育填 1，研究生教育填 2，其他填 0。
 * </p>
 */
class RdAchievement extends LongId with Coded with Named with Updated {

  /** 成果类型 */
  var achievementType: RdAchievementType = _

  /** 完成人 */
  var members: mutable.Buffer[RdAchievementMember] = Collections.newBuffer[RdAchievementMember]

  /** 完成单位 */
  var orgName: String = _

  /** 获奖情况 */
  var awards: mutable.Buffer[RdAchievementAward] = Collections.newBuffer[RdAchievementAward]

  /** 科类代码 */
  var categoryCode: Option[String] = None

  /** 成果网站 */
  var website: Option[String] = None

  /** 成果起始 */
  var beginOn: YearMonth = _

  /** 成果完成 */
  var endOn: YearMonth = _

  def memberNames: String = {
    members.map(_.name).mkString(",")
  }

  def getMember(idx: Int): Option[RdAchievementMember] = {
    members.find(_.idx == idx)
  }
}
