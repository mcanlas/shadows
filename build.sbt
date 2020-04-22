scalaVersion := "2.13.1"

libraryDependencies += "org.typelevel" %% "cats-effect" % "2.1.3"
libraryDependencies += "org.typelevel" %% "mouse"       % "0.25"

libraryDependencies += "org.scalatest"  %% "scalatest"  % "3.1.1"  % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.3" % "test"

scalafmtOnCompile := true

// scaladoc enhancements
scalacOptions in (Compile, doc) ++= Seq(
  "-groups",  // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
