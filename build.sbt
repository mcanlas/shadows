libraryDependencies += "org.typelevel" %% "cats-effect" % "3.3.14"

libraryDependencies += "org.scalatest"  %% "scalatest"  % "3.2.14" % "test"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.15.3" % "test"

// scaladoc enhancements
Compile / doc / scalacOptions ++= Seq(
  "-groups", // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
