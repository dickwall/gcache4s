name := """gcache4s"""

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
   "org.scalatest"              %% "scalatest"         % "2.2.4"       % "test"
  ,"com.google.guava"            % "guava"             % "18.0"
  ,"com.google.code.findbugs"    % "jsr305"            % "1.3.9"
)
