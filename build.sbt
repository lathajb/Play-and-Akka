name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val akkaVersion = "2.5.3"


scalaVersion := "2.11.8"
val lettuceVersion = "4.4.2.Final"
val guavaVersion = "19.0"

//libraryDependencies += javaJdbc
//libraryDependencies += cache
//libraryDependencies += javaWs
//libraryDependencies += guice

libraryDependencies += "com.google.inject" % "guice" % "4.0-beta"
libraryDependencies += "org.elasticsearch" % "elasticsearch" % "6.2.4"
libraryDependencies += "org.elasticsearch.client" % "transport" % "6.2.4"

dependencyOverrides += "io.netty" % "netty-codec-http" % "4.0.41.Final"
dependencyOverrides += "io.netty" % "netty-handler" % "4.0.41.Final"
dependencyOverrides += "io.netty" % "netty-codec" % "4.0.41.Final"
dependencyOverrides += "io.netty" % "netty-transport" % "4.0.41.Final"
dependencyOverrides += "io.netty" % "netty-buffer" % "4.0.41.Final"
dependencyOverrides += "io.netty" % "netty-common" % "4.0.41.Final"
dependencyOverrides += "io.netty" % "netty-transport-native-epoll" % "4.0.41.Final"
libraryDependencies += "org.apache.lucene" % "lucene-queries" % "6.2.4"
libraryDependencies += "org.apache.lucene" % "lucene-queryparser" % "6.2.4"
libraryDependencies += "org.apache.lucene" % "lucene-suggest" % "6.2.4"
libraryDependencies += "org.apache.lucene" % "lucene-highlighter" % "6.2.4"
libraryDependencies += "org.apache.lucene" % "lucene-spatial-extras" % "6.2.4"
libraryDependencies += "org.apache.lucene" % "lucene-spatial3d" % "6.2.4"
libraryDependencies += "org.apache.lucene" % "lucene-spatial" % "6.2.4"

libraryDependencies +=  "biz.paluch.redis" % "lettuce" % lettuceVersion
libraryDependencies +=  "com.google.guava" % "guava" % guavaVersion
libraryDependencies +=  "junit" % "junit" % "4.12"
libraryDependencies += "com.google.code.gson" % "gson" % "2.2.4"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.2"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.2"

