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

package org.openurp.starter.web.helper

import jakarta.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.beangle.commons.lang.Numbers
import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.beangle.ems.app.web.EmsCookie
import org.beangle.security.Securities
import org.beangle.security.authc.{DefaultAccount, Profile}
import org.openurp.base.model.{Project, School}

import java.time.LocalDate

class EmsCookieHelper(entityDao: EntityDao) {

  def getSchool(req: HttpServletRequest, res: HttpServletResponse): School = {
    findSchoolByServerName(req) match {
      case Some(s) => s
      case None =>
        val cookie = EmsCookie.get(req, res)
        Securities.session match {
          case Some(s) =>
            val account = s.principal.asInstanceOf[DefaultAccount]
            var profile: Profile = null
            if (null != account.profiles && account.profiles.length > 0) {
              if (cookie.profile == 0L) {
                profile = account.profiles(0)
              } else {
                profile = account.profiles.find(p => p.id == cookie.profile).getOrElse(account.profiles(0))
              }
              if (profile.id != cookie.profile) {
                cookie.profile = profile.id
                EmsCookie.update(req, res, cookie, true)
              }
            }
            if (null == profile) {
              getFirstSchool()
            } else {
              val pstr = profile.getProperty("school").orNull
              if (null != pstr) {
                if (pstr == "*") {
                  getFirstSchool()
                } else if (Numbers.isDigits(pstr)) {
                  entityDao.get(classOf[School], pstr.toInt)
                } else {
                  null
                }
              } else {
                getFirstSchool()
              }
            }
          case None => null
        }
    }
  }

  private def findSchoolByServerName(request: HttpServletRequest): Option[School] = {
    val builder = OqlBuilder.from(classOf[School], "p").where("p.code =:pcode", request.getServerName)
      .cacheable()
    entityDao.search(builder).headOption
  }

  private def getFirstSchool(): School = {
    val query = OqlBuilder.from(classOf[School], "p")
    query.where("p.endOn is null or p.endOn > :today", LocalDate.now)
    query.orderBy("p.code").cacheable()
    entityDao.search(query).head
  }

  def getProject(req: HttpServletRequest, res: HttpServletResponse): Project = {
    getProfile(req, res) match {
      case Some(p) =>
        val pstr = p.getProperty("project").orNull
        if (null != pstr) {
          if (pstr == "*") {
            getFirstProject()
          } else if (Numbers.isDigits(pstr)) {
            entityDao.get(classOf[Project], pstr.toInt)
          } else {
            null
          }
        } else {
          getFirstProject()
        }
      case None =>
        if (Securities.session.isDefined) {
          getFirstProject()
        } else {
          null
        }
    }
  }

  def getProfile(req: HttpServletRequest, res: HttpServletResponse): Option[Profile] = {
    Securities.session match {
      case Some(s) =>
        val account = s.principal.asInstanceOf[DefaultAccount]
        var profile: Profile = null
        if (null != account.profiles && account.profiles.length > 0) {
          val cookie = EmsCookie.get(req, res)
          if (cookie.profile == 0L) {
            profile = account.profiles(0)
          } else {
            profile = account.profiles.find(p => p.id == cookie.profile).getOrElse(account.profiles(0))
          }
          if (profile.id != cookie.profile) {
            cookie.profile = profile.id
            EmsCookie.update(req, res, cookie, true)
          }
        }
        Option(profile)
      case None => None
    }
  }

  private def getFirstProject(): Project = {
    val query = OqlBuilder.from(classOf[Project], "p")
    query.where("p.endOn is null or p.endOn > :today", LocalDate.now)
    query.orderBy("p.code").cacheable()
    entityDao.search(query).head
  }
}
