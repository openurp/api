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

import org.beangle.data.orm.MappingModule

class DefaultMapping extends MappingModule {

  def binding(): Unit = {
    bind[ThesisPlan].declare { e =>
      e.departPlans is depends("thesisPlan")
    }

    bind[DepartPlan].declare { e =>
    }

    bind[Advisor].declare { e =>
      e.description is length(4000)
      index("", true, e.project, e.user)
    }

    bind[Subject] declare { e =>
      e.name is length(300)
      e.requirements is length(1000)
      e.conditions is length(1000)
      e.contents is length(1000)
      index("", true, e.season, e.name)
    }

    bind[Writer].declare { e =>
      e.deadlines is depends("writer")
      index("", true, e.std, e.season)
      index("", false, e.season)
      index("", false, e.std)
    }

    bind[SubjectApply].declare { e =>
      index("", true, e.writer)
    }

    bind[Commitment].declare { e =>
      index("", true, e.writer)
    }

    bind[Proposal].declare { e =>
      e.references is column("refers")
      e.methods & e.conditions & e.outline & e.advisorOpinion & e.meanings & e.references are lob

      index("", true, e.writer)
    }

    bind[Guidance].declare { e =>
      e.contents is lob
      index("", true, e.writer)
    }

    bind[MidtermCheck].declare { e =>
      e.details is depends("check")
      e.proceeding is lob
      index("", true, e.writer)
    }

    bind[MidtermCheckDetail].declare { e =>
      e.auditOpinion is length(1000)
    }

    bind[MidtermCheckItem]
    bind[Deadline]
    bind[ThesisPaper].declare { e =>
      index("", true, e.writer)
    }

    bind[ThesisReview].declare { e =>
      e.crossReviewOpinion is length(2000)
      index("", true, e.writer)
    }

    bind[DefenseInfo].declare { e =>
      index("", true, e.writer)
    }

    bind[DefenseGroup].declare { e =>
      e.members is depends("group")
      e.notices is depends("group")
    }

    bind[DefenseMember]

    bind[DefenseNotice].declare { e =>
      e.contents is lob
    }

    bind[CopyCheck].declare { e =>
      e.report is length(200)
    }
  }
}
