scalaVersion := "2.13.1"

libraryDependencies += "org.typelevel" %% "cats-effect" % "2.0.0"
libraryDependencies += "org.typelevel" %% "mouse"       % "0.23"

libraryDependencies += "org.scalatest"  %% "scalatest"  % "3.1.0"  % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.2" % "test"

scalafmtOnCompile := true

// scaladoc enhancements
scalacOptions in (Compile, doc) ++= Seq(
  "-groups",  // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
