scalaVersion := "2.13.6"

libraryDependencies += "org.typelevel" %% "cats-effect" % "3.2.9"
libraryDependencies += "org.typelevel" %% "mouse"       % "0.26"

libraryDependencies += "org.scalatest"  %% "scalatest"  % "3.2.10"  % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.3" % "test"

scalafmtOnCompile := true

// scaladoc enhancements
Compile / doc / scalacOptions ++= Seq(
  "-groups", // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
