scalaVersion := "2.12.8"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.27"

scalafmtOnCompile := true

// scaladoc enhancements
scalacOptions in (Compile, doc) ++= Seq(
  "-groups",  // enable support for grouped members
  "-diagrams" // generate type hierarchy diagrams
)
