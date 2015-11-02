package org.openurp.people.contact.model

import org.beangle.commons.collection.Collections
import scala.collection.mutable.Buffer

trait Typed {

  var typenames: String = _

}

trait ValueTypes {

  def valuetypes: Set[String]
}

trait TextType extends ValueTypes {
  def valuetypes: Set[String] = {
    Set("text")
  }
}

trait UriType extends ValueTypes {
  def valuetypes: Set[String] = {
    Set("uri")
  }
}

trait UriTextType extends ValueTypes {
  def valuetypes: Set[String] = {
    Set("text", "uri")
  }
}

trait ContactAware{
  var contact:Contact =_
}