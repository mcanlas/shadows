scalaVersion := "2.12.8"

libraryDependencies += "org.typelevel" %% "cats-effect" % "1.3.0"
libraryDependencies += "org.typelevel" %% "mouse" % "0.21"

libraryDependencies += "org.scalatest"  %% "scalatest"  % "3.0.7"  % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % "test"

scalafmtOnCompile := true

// scaladoc enhancements
scalacOptions in (Compile, doc) ++= Seq(
  "-groups",  // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
