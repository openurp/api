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

package org.openurp.base.service

import org.openurp.base.hr.model.{Secretary, Staff, Teacher}
import org.openurp.base.model.User
import org.openurp.base.std.model.Student

trait UserRepo {

  def createUser(staff: Staff, oldCode: Option[String]): User

  def createUser(teacher: Teacher): User

  def createUser(secretary: Secretary): Unit

  def createUser(std: Student, oldCode: Option[String]): User

  def createAccount(user: User): Unit

  def activate(users: Iterable[User], active: Boolean): Unit

  def updatePassword(userCode: String, password: String): Int
}
