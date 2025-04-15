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

package org.openurp.base.edu.model

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class TermsTest extends AnyFunSpec, Matchers {
  describe("Terms") {
    it("list empty") {
      val empty = Terms.empty
      empty.termList.size should be(0)
    }
    it("list 1") {
      val multiple = Terms("1,2,4")
      println(multiple.termList)
      multiple.termList should equal(List(1, 2, 4))
    }
  }
}
