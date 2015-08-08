name := "MLEngine"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.mongodb" %% "casbah" % "2.7.3",
  "org.apache.spark" %% "spark-core" % "1.4.1" % "provided"
)
