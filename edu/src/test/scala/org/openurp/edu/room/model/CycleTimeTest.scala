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

package org.openurp.edu.room.model

import org.beangle.commons.lang.time.HourMinute
import org.openurp.edu.room.model.CycleTime.CycleTimeType
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import java.time.LocalDate

class CycleTimeTest extends AnyFunSpec with Matchers {
  describe("CycleTime") {
    it("digest every day") {
      val ct = CycleTime(LocalDate.parse("2023-07-04"), LocalDate.parse("2023-07-09"), HourMinute("08:00"), HourMinute("21:00"))
      val content = CycleTimeDigest.digest(ct.convert(), "\n")
      assert(content == "2023-07-04~07-09 每天 08:00~21:00")
    }

    it("digest 2 day in week") {
      val ct = CycleTime(LocalDate.parse("2023-07-04"), LocalDate.parse("2023-07-05"), HourMinute("08:00"), HourMinute("21:00"))
      val ct2 = CycleTime(LocalDate.parse("2023-07-11"), LocalDate.parse("2023-07-12"), HourMinute("08:00"), HourMinute("21:00"))
      val rs = ct.convert()
      val content = CycleTimeDigest.digest(rs ++ ct2.convert(), ",")
      assert(content == "2023-07-04~07-11 每周二 08:00~21:00,2023-07-05~07-12 每周三 08:00~21:00")
    }

    it("digest every week") {
      val ct = CycleTime(LocalDate.parse("2023-07-04"), LocalDate.parse("2023-08-01"),
        HourMinute("08:00"), HourMinute("21:00"), 1, CycleTimeType.Week)
      val content = CycleTimeDigest.digest(ct.convert(), ",")
      assert(content == "2023-07-04~08-01 每周二 08:00~21:00")
    }
    it("digest every 3 days week") {
      val ct = CycleTime(LocalDate.parse("2023-07-04"), LocalDate.parse("2023-08-01"),
        HourMinute("08:00"), HourMinute("21:00"), 3, CycleTimeType.Day)
      val content = CycleTimeDigest.digest(ct.convert(), ",")
      assert(content == "2023-07-04~07-31 每3天 08:00~21:00")
    }
  }

}
