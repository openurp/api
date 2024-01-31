import org.openurp.parent.Dependencies.*
import org.openurp.parent.Settings.*

ThisBuild / organization := "org.openurp.api"
ThisBuild / version := "0.37.0"

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/openurp/api"),
    "scm:git@github.com:openurp/api.git"
  )
)

ThisBuild / developers := List(
  Developer(
    id = "chaostone",
    name = "Tihua Duan",
    email = "duantihua@gmail.com",
    url = url("http://github.com/duantihua")
  )
)

ThisBuild / description := "OpenURP API"
ThisBuild / homepage := Some(url("http://openurp.github.io/api/index.html"))
ThisBuild / resolvers += Resolver.mavenLocal

lazy val root = (project in file("."))
  .settings()
  .aggregate(code, base, edu, prac, qos, trd, spa, std, degree, all)

lazy val code = (project in file("code"))
  .settings(
    organization := "org.openurp.code",
    name := "openurp-code-api",
    common,
    libraryDependencies ++= Seq(scalatest, beangle_commons_core, beangle_data_orm)
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

lazy val spa = (project in file("spa"))
  .settings(
    organization := "org.openurp.spa",
    name := "openurp-spa-api",
    common
  ).dependsOn(base)

lazy val std = (project in file("std"))
  .settings(
    organization := "org.openurp.std",
    name := "openurp-std-api",
    common
  ).dependsOn(base)

lazy val all = (project in file("all"))
  .settings(
    organization := "org.openurp",
    name := "openurp-api-all",
    common,
    libraryDependencies ++= Seq(scalatest, logback_classic),
    publish / skip := true
  ).dependsOn(code, base, edu, prac, qos, trd, spa, std, degree)

publish / skip := true
