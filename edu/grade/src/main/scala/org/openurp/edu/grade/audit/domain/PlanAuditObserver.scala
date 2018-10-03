/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2005, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openurp.edu.grade.audit.domain

/**
 * 在审核的时候Observer的notifyBegin先执行<br>
 * 然后是Listener<br>
 * 然后是Observer的notifyEnd
 */
trait PlanAuditObserver {

  def notifyStart(): Unit

  def notifyBegin(context: PlanAuditContext, index: Int): Boolean

  def notifyEnd(context: PlanAuditContext, index: Int): Unit

  def finish(): Unit
}
