package org.openurp.api

import org.hibernate.dialect.PostgreSQL9Dialect
import org.beangle.data.hibernate.tool.DdlGenerator
import org.hibernate.dialect.PostgreSQL9Dialect

object SqlGenerator {
  def main(args: Array[String]): Unit = {
    DdlGenerator.main(Array(classOf[PostgreSQL9Dialect].getName(), "/tmp", "zh_CN", null));
  }
}