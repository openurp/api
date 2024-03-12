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

package org.openurp.degree.thesis.model

enum Stage(val id: Int, val name: String, val subCount: Int = 0) {
  case Subject extends Stage(1, "论文题目提交")
  case SubjectReview extends Stage(2, "论文题目审查")

  case SubjectChosen extends Stage(10, "学生选题", 2)
  case SubjectChosenRound1 extends Stage(11, "学生初选")
  case SubjectChosenRound2 extends Stage(12, "学生补选")
  case Commitment extends Stage(13, "确认任务书")
  case Proposal extends Stage(14, "撰写开题报告")
  case Guidance extends Stage(15, "教师指导", 2)
  case Guidance1 extends Stage(16, "教师指导Ⅰ")
  case MidtermCheck extends Stage(17, "中期检查")
  case Guidance2 extends Stage(18, "教师指导Ⅱ")

  case ThesisDraftSubmit extends Stage(20, "初稿论文提交")
  case ThesisFinalSubmit extends Stage(21, "终稿提交")

  case ThesisReview extends Stage(31, "论文评阅") //21=>31 22=>32
  case OralDefense extends Stage(32, "论文答辩")

  case ThesisArchiveSubmit extends Stage(40, "归档提交")

  override def toString: String = name
}
