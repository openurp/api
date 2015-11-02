package org.openurp.people.contact.model

import org.beangle.data.model.{ LongId, Named }

/**
 * 绰号/称呼
 */
class Nickname extends LongId with Preferred with Typed with TextType with ContactAware with Named {
}