package org.openurp.edu.program.plan.model

class Terms(val value: Int) extends Ordered[Terms] with Serializable {

  override def compare(other: Terms): Int = {
    if (this.value < other.value) -1
    else if (this.value == other.value) 0
    else 1
  }

  def termList: List[Int] = {
    val str = toString
    var i = str.length - 1
    val result = new collection.mutable.ListBuffer[Int]
    while (i >= 0) {
      if (str.charAt(i) == '1') result += (str.length - i)
      i -= 1
    }
    result.toList
  }

  def matches(other: Terms): Boolean = {
    (this.value & other.value) > 0
  }
}