scalaVersion := "2.13.3"

libraryDependencies += "org.typelevel" %% "cats-effect" % "2.2.0-RC3"
libraryDependencies += "org.typelevel" %% "mouse"       % "0.25"

libraryDependencies += "org.scalatest"  %% "scalatest"  % "3.2.1"  % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.3" % "test"

scalafmtOnCompile := true

// scaladoc enhancements
scalacOptions in (Compile, doc) ++= Seq(
  "-groups",  // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
