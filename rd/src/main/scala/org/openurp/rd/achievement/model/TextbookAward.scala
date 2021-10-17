/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.rd.achievement.model

import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Named
import org.openurp.rd.code.model.{RdAwardGrade, RdLevel}

import java.time.YearMonth

/** 教材获奖 */
class TextbookAward extends LongId with Named {

  /** 教材成果 */
  var achievement: TextbookAchievement = _

  /** 获奖年月 */
  var awardOn: Option[YearMonth] = None

  /** 获奖等级 */
  var grade: Option[RdAwardGrade] = None

  /** 获奖级别 */
  var level: RdLevel = _
}
