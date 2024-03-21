version := "0.1.0-SNAPSHOT"
name := "AmberWords"
organization := "com.swarren"
ThisBuild / scalaVersion := "2.13.13"
//mainClass := Some("com.swarren.amber.Main")
lazy val root = (project in file("."))
  .settings(
    name := "AmberWords"
  )

resolvers += Resolver.mavenLocal
version := "1.0"
libraryDependencies := Seq(
  "org.scala-lang" % "scala-library" % "2.13.13",
  "org.json4s" %% "json4s-native" % "4.0.7",
  "org.json4s" %% "json4s-jackson" % "4.0.7",
  "org.scalatest" %% "scalatest" % "3.2.14" % "test",
  "org.scalameta" %% "munit" % "0.7.26" % "test"

)
