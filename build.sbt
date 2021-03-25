scalaVersion := "2.13.5"

libraryDependencies += "org.typelevel" %% "cats-effect" % "3.0.0-RC3"
libraryDependencies += "org.typelevel" %% "mouse"       % "0.25"

libraryDependencies += "org.scalatest"  %% "scalatest"  % "3.2.6"  % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.3" % "test"

scalafmtOnCompile := true

// scaladoc enhancements
Compile / doc / scalacOptions ++= Seq(
  "-groups",  // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
