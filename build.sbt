import org.openurp.parent.Dependencies.*
import org.openurp.parent.Settings.*

organization := "org.openurp"
version := "1.4.13"

scmInfo := Some(
  ScmInfo(
    uri("https://github.com/openurp/api"),
    "scm:git@github.com:openurp/api.git"
  )
)

developers := List(
  Developer(
    id = "chaostone",
    name = "Tihua Duan",
    email = "duantihua@gmail.com",
    url = uri("http://github.com/duantihua")
  )
)

description := "OpenURP API"
homepage := Some(uri("http://openurp.github.io/api/index.html"))
resolvers += Resolver.mavenLocal

val ojdbc11 = "com.oracle.database.jdbc" % "ojdbc11" % "23.26.3.0.0"
val orai18n = "com.oracle.database.nls" % "orai18n" % "23.26.3.0.0"

val startLibs = Seq(beangle_commons, beangle_ems_app, beangle_data_hibernate, beangle_cdi, beangle_jdbc, beangle_config,
  logback_classic, caffeine_jcache, ojdbc11, orai18n)

lazy val root = (project in file("."))
  .settings(
    name := "openurp-api",
    common,
    publish / skip := true)
  .aggregate(code, base, edu, prac, qos, trd, std, degree, lab, starter_task, starter_web, all)

lazy val code = (project in file("code"))
  .settings(
    organization := "org.openurp.code",
    name := "openurp-code-api",
    common,
    libraryDependencies ++= Seq(scalatest, beangle_commons, beangle_data_model, beangle_jdbc, beangle_config)
  )

lazy val base = (project in file("base"))
  .settings(
    organization := "org.openurp.base",
    name := "openurp-base-api",
    common
  ).dependsOn(code)

lazy val edu = (project in file("edu"))
  .settings(
    organization := "org.openurp.edu",
    name := "openurp-edu-api",
    common
  ).dependsOn(base)

lazy val degree = (project in file("degree"))
  .settings(
    organization := "org.openurp.degree",
    name := "openurp-degree-api",
    common
  ).dependsOn(base)

lazy val prac = (project in file("prac"))
  .settings(
    organization := "org.openurp.prac",
    name := "openurp-prac-api",
    common
  ).dependsOn(base)

lazy val qos = (project in file("qos"))
  .settings(
    organization := "org.openurp.qos",
    name := "openurp-qos-api",
    common
  ).dependsOn(edu)

lazy val trd = (project in file("trd"))
  .settings(
    organization := "org.openurp.trd",
    name := "openurp-trd-api",
    common
  ).dependsOn(base)

lazy val std = (project in file("std"))
  .settings(
    organization := "org.openurp.std",
    name := "openurp-std-api",
    common
  ).dependsOn(base, edu)

lazy val lab = (project in file("lab"))
  .settings(
    organization := "org.openurp.lab",
    name := "openurp-lab-api",
    common
  ).dependsOn(base)

lazy val all = (project in file("all"))
  .settings(
    organization := "org.openurp",
    name := "openurp-api-all",
    common,
    libraryDependencies ++= Seq(scalatest, logback_classic),
    publish / skip := true
  ).dependsOn(code, base, edu, prac, qos, trd, std, degree, lab)

lazy val starter_web = (project in file("starter/web"))
  .settings(
    organization := "org.openurp.starter",
    name := "openurp-starter-web",
    common,
    libraryDependencies ++= startLibs,
    libraryDependencies ++= Seq(beangle_bui_bootstrap),
    libraryDependencies ++= Seq(beangle_she, beangle_transfer, beangle_webmvc),
    libraryDependencies ++= Seq(protobuf, beangle_cron)
  ).dependsOn(base)

lazy val starter_task = (project in file("starter/task"))
  .settings(
    organization := "org.openurp.starter",
    name := "openurp-starter-task",
    common,
    libraryDependencies ++= startLibs
  ).dependsOn(base)
