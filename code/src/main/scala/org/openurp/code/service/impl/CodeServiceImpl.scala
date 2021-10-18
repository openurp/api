/*
 * Copyright (C) 2005, The OpenURP Software.
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

package org.openurp.code.service.impl

import org.beangle.data.dao.EntityDao
import org.beangle.data.dao.OqlBuilder
import org.openurp.code.service.CodeService
import org.openurp.code.Code
import java.time.LocalDate

class CodeServiceImpl extends CodeService {
  var entityDao: EntityDao = _

  def get[T <: Code](clazz: Class[T], code: String): Option[T] = {
    val builder = OqlBuilder.from(clazz, "basecode").where("basecode.code=:code", code);
    val rs = entityDao.search(builder)
    if (rs.nonEmpty) Some(rs.head)
    else None
  }

  def get[T <: Code](clazz: Class[T]): collection.Seq[T] = {
    val now = LocalDate.now
    val builder = OqlBuilder.from(clazz, "basecode")
      .where("basecode.beginOn <= :now and (basecode.endOn is null or basecode.endOn >= :now)", now);
    builder.orderBy("basecode.code").cacheable()
    entityDao.search(builder)
  }

  def get[T <: Code](clazz: Class[T], id: Int): T = {
    entityDao.get(clazz, id)
  }

  def get[T <: Code](clazz: Class[T], ids: Int*): collection.Seq[T] = {
    val builder = OqlBuilder.from(clazz, "basecode").where("basecode.id in(:ids)", ids);
    entityDao.search(builder)
  }
}
