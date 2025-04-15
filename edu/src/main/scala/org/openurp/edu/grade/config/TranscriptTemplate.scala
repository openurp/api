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

package org.openurp.edu.grade.config

import org.beangle.data.model.LongId
import org.beangle.data.model.annotation.config
import org.beangle.data.model.pojo.{Coded, Named, Remark, Updated}
import org.openurp.base.model.Project

/**
 * 系统报表模板定义<br>
 * 系统报表是项目范围内的各类业务的自定义报表定义。项目属性为空的，为缺省模板。
 * <ul>
 * <li>category 表示种类例如成绩部分、计划部分</li>
 * <li>code表示该报表代码</li>
 * <li>name表示该报表的名称</li>
 * <li>remark表示该报表的说明</li>
 * <li>template表示该报表的模板或者路径说明</li>
 * </ul>
 * 项目和代码 联合唯一
 */
@config
class TranscriptTemplate extends LongId, Updated, Remark, Coded, Named {

  /** 项目 */
  var project: Project = _

  /** 类别 */
  var category: String = _

  /** 模板路径 */
  var template: String = _

  /** 选项 */
  var options: String = _

  /** 纸张大小 */
  var pageSize: String = "A4"

  /** 横向Portrait/纵向Landscape */
  var orientation: String = "Portrait"
}
