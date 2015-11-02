package org.openurp.people.contact.model

import org.beangle.data.model.LongId
import org.beangle.data.model.Named

/**
 * 头衔
 */
class Title extends LongId with Preferred with Typed with Named  with TextType with ContactAware {
}