name := """HearthApi"""
organization := "com.waffelmonster"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

packageName in Universal := "hearth-api"

scalaVersion := "2.12.0"

libraryDependencies += guice

libraryDependencies += "io.swagger" %% "swagger-play2" % "1.7.1"

// Database stuff
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
libraryDependencies += evolutions
libraryDependencies += "com.typesafe.play" %% "play-slick" % "4.0.2"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "4.0.2"
libraryDependencies += "com.h2database" % "h2" % "1.4.197"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += "org.scalamock" %% "scalamock" % "4.1.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.waffelmonster.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.waffelmonster.binders._"
