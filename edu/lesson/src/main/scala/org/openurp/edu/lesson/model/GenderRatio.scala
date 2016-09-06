package org.openurp.edu.lesson.model

import org.beangle.commons.lang.annotation.value

object GenderRatio {
  val empty = new GenderRatio(0)
}
@value
class GenderRatio(val value: Short) extends Ordered[GenderRatio] with Serializable {
  override def compare(other: GenderRatio): Int = {
    if (this.value < other.value) -1
    else if (this.value == other.value) 0
    else 1
  }
}