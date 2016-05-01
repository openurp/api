package org.openurp.edu.grade.course.service

import org.openurp.base.model.Semester
import org.openurp.edu.base.model.Student
import org.openurp.edu.grade.course.model.{ CourseGrade, StdGpa }

trait CourseGradeService {

  def getGrades(std: Student, semesters: Semester*): Seq[CourseGrade]
  /**
   * 查询一批学生发布的成绩
   */
  def getGrades(stds: Iterable[Student], semesters: Semester*): collection.Map[Student, Seq[CourseGrade]]

  /**
   * 如果semesters不包含元素或者为null则统计所有学期 否则统计学生的在校semesters所包含的学期的平均绩点
   *
   * @param std
   * @return
   */
  def getGpa(std: Student, semesters: Semester*): StdGpa

  /**
   * 查看学生各个课程的通过状态
   */
  def getPassedStatus(std: Student): collection.Map[java.lang.Long, Boolean]
}