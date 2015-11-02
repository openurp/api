/*
 * OpenURP, Agile University Resource Planning Solution
 *
 * Copyright (c) 2014-2015, OpenURP Software.
 *
 * OpenURP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenURP is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenURP.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.base.model

import scala.reflect.runtime.universe

import org.beangle.data.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")
    defaultCache("openurp.base", "read-write")

    bind[School] on (e => declare(
      e.code is (notnull, length(10), unique),
      e.name is (notnull, length(50))))

    bind[Department] on (e => declare(
      e.code is (notnull, length(10), unique),
      e.name is (notnull, length(80)),
      e.enName is (length(100)),
      e.shortName is (length(100)),
      e.indexno is (notnull, length(20), unique),
      e.children is (depends("parent")),
      e.remark is (length(200))))

    bind[Calendar] on (e => declare(
      e.semesters is (depends("calendar")),
      e.code is (notnull, length(10)),
      e.name is (notnull, length(80)),
      e.firstWeekday is (notnull)))

    bind[Semester].on(e => declare(
      e.code is (notnull, length(15), unique),
      e.name is (notnull, length(10)),
      e.schoolYear is (notnull, length(10)),
      e.calendar is (notnull))).generator("code")

    bind[Campus] on (e => declare(
      e.code is (notnull, length(10), unique),
      e.name is (notnull, length(80)),
      e.enName & e.shortName are (length(100)),
      e.remark is (length(200))))

    bind[Building] on (e => declare(
      e.code is (notnull, length(10), unique),
      e.name is (notnull, length(80)),
      e.enName & e.shortName are (length(100)),
      e.campus is (notnull),
      e.remark is (length(200))))

    bind[Room] on (e => declare(
      e.code is (notnull, length(10), unique),
      e.name is (notnull, length(80)),
      e.remark is (length(200)),
      e.campus is (notnull)))

    bind[Holiday] on (e => declare(
      e.name is (notnull, length(20)),
      e.beginOn & e.endOn are (notnull)))

    bind[TimeSetting] on (e => declare(
      e.name is (notnull, length(20)),
      e.units is (depends("setting"))))

    bind[CourseUnit] on (e => declare(
      e.enName is (notnull, length(30)),
      e.name & e.indexno are (notnull, length(20)),
      e.beginAt & e.endAt & e.setting are (notnull)))

    bind[User] on (e => declare(
      e.code is (notnull, length(30)),
      e.name is (notnull, length(80)),
      e.email is (length(80)),
      e.mobile is (length(15)),
      e.remark is (length(200)),
      e.department & e.category are (notnull)))
  }

  all.except(classOf[User]).cacheable()
}
