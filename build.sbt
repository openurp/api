import org.openurp.parent.Dependencies.*
import org.openurp.parent.Settings.*

ThisBuild / organization := "org.openurp"
ThisBuild / version := "0.46.1-SNAPSHOT"

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
  .settings(
    name := "openurp-api",
    common)
  .aggregate(code, base, edu, prac, qos, trd, std, degree, lab, all)

lazy val code = (project in file("code"))
  .settings(
    organization := "org.openurp.code",
    name := "openurp-code-api",
    common,
    libraryDependencies ++= Seq(scalatest, beangle_commons, beangle_model, beangle_jdbc)
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

publish / skip := true
