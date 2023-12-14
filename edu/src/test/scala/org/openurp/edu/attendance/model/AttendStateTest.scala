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

package org.openurp.edu.attendance.model

import org.openurp.edu.attendance.model.AttendState.{Absent, Leave, LeaveEarly}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers


class AttendStateTest extends AnyFunSpec with Matchers {
  describe("AttendState") {
    it("set") {
      var a = AttendStates.Empty.add(1, Absent)
      a = a.add(3, Leave)
      a = a.add(5, LeaveEarly)
      assert(a(1) == Absent)
      assert(a(3) == Leave)
      a=a.remove(5)

      val b = AttendStates(Absent, LeaveEarly, Leave)
      assert(b.values.size == 3)
      assert(b(1) == Absent)
      assert(b(2) == LeaveEarly)
      assert(b(3) == Leave)
    }
  }
}
