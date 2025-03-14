libraryDependencies += "org.typelevel" %% "cats-effect" % "3.6.0-RC2"

libraryDependencies += "org.scalatest"  %% "scalatest"  % "3.2.16" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.17.0" % "test"

// scaladoc enhancements
Compile / doc / scalacOptions ++= Seq(
  "-groups",  // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
