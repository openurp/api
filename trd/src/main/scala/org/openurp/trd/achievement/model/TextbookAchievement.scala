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

package org.openurp.trd.achievement.model

import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.Updated
import org.openurp.base.edu.model.Textbook

import scala.collection.mutable

/** 教材信息
 *
 */
class TextbookAchievement extends LongId with Updated {

  /** 教材 */
  var textbook: Textbook = _

  /** 编写作者 */
  var editors: mutable.Buffer[TextbookEditor] = Collections.newBuffer[TextbookEditor]

  /** 获奖信息 */
  var awards: mutable.Buffer[TextbookAward] = Collections.newBuffer[TextbookAward]

  def chiefEditors: collection.Seq[TextbookEditor] = {
    editors.filter(_.chief)
  }

  def noChiefEditors: collection.Seq[TextbookEditor] = {
    editors.filter(_.chief)
  }

  def getEditor(chief: Boolean, idx: Int): Option[TextbookEditor] = {
    editors.find(x => x.idx == idx && x.chief == chief)
  }
}
