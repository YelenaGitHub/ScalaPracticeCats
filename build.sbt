name := "ScalaPracticeCats"

version := "0.1"

scalaVersion := "2.13.3"

val catsVersion = "2.1.1"
val catsEffectVersion = "2.1.4"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion,
  "org.typelevel" %% "cats-effect" % catsVersion
)