lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.13.3"
    )),
    name := "scalatest-example"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
libraryDependencies += "org.scalamock" %% "scalamock" % "5.1.0" % Test
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.1" % "test"
