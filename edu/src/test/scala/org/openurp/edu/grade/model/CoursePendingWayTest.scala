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

package org.openurp.edu.grade.model

import org.beangle.commons.lang.Enums
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class CoursePendingWayTest extends AnyFunSpec, Matchers {
  describe("CoursePendingWay") {
    it("persist by id") {
      Enums.id(CoursePendingWay.Taking) should be(1)
      Enums.id(CoursePendingWay.Makeup) should be(2)
      Enums.id(CoursePendingWay.GraduateYear) should be(3)
      Enums.of(classOf[CoursePendingWay], 1) should be(Some(CoursePendingWay.Taking))
      Enums.of(classOf[CoursePendingWay], 2) should be(Some(CoursePendingWay.Makeup))
      Enums.of(classOf[CoursePendingWay], 3) should be(Some(CoursePendingWay.GraduateYear))
      CoursePendingWay.Taking.name should be("在读")
      CoursePendingWay.Makeup.name should be("补缓考")
      CoursePendingWay.GraduateYear.name should be("毕业学年课程")
    }
  }
}
