scalaVersion := "2.13.0"

libraryDependencies += "org.typelevel" %% "cats-effect" % "2.0.0-M5"
libraryDependencies += "org.typelevel" %% "mouse" % "0.22"

libraryDependencies += "org.scalatest"  %% "scalatest"  % "3.0.8"  % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % "test"

scalafmtOnCompile := true

// scaladoc enhancements
scalacOptions in (Compile, doc) ++= Seq(
  "-groups",  // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
