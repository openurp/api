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

package org.openurp.starter.task

import org.beangle.commons.cdi.BindModule
import org.beangle.data.dao.EntityDao
import org.beangle.data.hibernate.tx.{HibernateTransactionManager, TransactionProxyFactoryBean}
import org.beangle.data.hibernate.{DomainFactory, HibernateEntityDao, LocalSessionFactoryBean, SessionCleaner}
import org.hibernate.SessionFactory

class OrmModule extends BindModule {

  protected override def binding(): Unit = {
    wiredEagerly(false)

    bind("SessionFactory.default", classOf[LocalSessionFactoryBean])
      .property("devMode", devEnabled)
      .property("ormLocation", "classpath*:beangle.xml")
      .primaryOf(classOf[SessionFactory]).on(missing(classOf[SessionFactory]))

    bind("HibernateTransactionManager.default", classOf[HibernateTransactionManager])

    //配置事务模板，不需要的方法不配置，例如find*
    bind("TransactionProxy.template", classOf[TransactionProxyFactoryBean]).setAbstract()
      .property("transactionAttributes",
        props("save*=PROPAGATION_REQUIRED", "update*=PROPAGATION_REQUIRED", "delete*=PROPAGATION_REQUIRED",
          "batch*=PROPAGATION_REQUIRED", "execute*=PROPAGATION_REQUIRED", "remove*=PROPAGATION_REQUIRED",
          "create*=PROPAGATION_REQUIRED", "init*=PROPAGATION_REQUIRED", "authorize*=PROPAGATION_REQUIRED"))
      .property("transactionManager", ref("HibernateTransactionManager.default"))

    bind(classOf[DomainFactory]).constructor(list(ref("SessionFactory.default")))

    bind("EntityDao.hibernate", classOf[TransactionProxyFactoryBean]).proxy("target", classOf[HibernateEntityDao])
      .parent("TransactionProxy.template").primaryOf(classOf[EntityDao]).description("基于Hibernate提供的通用DAO")
      .property("proxyInterfaces", Array(classOf[EntityDao]))
      .property("proxyTargetClass", false)
      .nowire()

    bind(classOf[SessionCleaner])
  }

}
