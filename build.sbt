
name := "scala-redshift"

version := "1.0"

scalaVersion := "2.11.8"


resolvers += Resolver.sonatypeRepo("releases")
libraryDependencies += "com.github.melrief" %% "purecsv" % "0.0.6"


libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"         % "2.4.2",
  "org.scalikejdbc" %% "scalikejdbc-config"  % "2.4.2",
  "org.scalikejdbc" %% "scalikejdbc-test"    % "2.4.2"   % "test",
  "org.slf4j" % "slf4j-simple" % "1.7.21"
)

libraryDependencies += "org.apache.commons" % "commons-csv" % "1.1"