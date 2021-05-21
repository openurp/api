package org.openurp.base.edu.model

import org.junit.runner.RunWith
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TermsTest extends AnyFunSpec with Matchers {
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
