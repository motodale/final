name := "Wow auction house"

version := "1.0"

scalaVersion := "2.9.1"

resolvers ++= Seq("Banno Snapshots Repo" at "http://nexus.banno.com/nexus/content/repositories/snapshots",
                   "Banno Releases Repo" at "http://nexus.banno.com/nexus/content/repositories/releases",
                   "Banno External Repo" at "http://nexus.banno.com/nexus/content/groups/external/")

libraryDependencies ++= Seq("net.databinder" %% "dispatch-http-json" % "0.8.8","net.databinder" %% "dispatch-json" % "0.8.8","net.databinder" %% "dispatch-http" % "0.8.8" )
