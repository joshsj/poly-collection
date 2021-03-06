name := "poly-collection"
version := "0.1.0-SNAPSHOT"
isSnapshot := true
organization := "me.tongfei"
scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.8", "2.12.1")

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "me.tongfei"        %% "poly-algebra"    % "0.4.0-SNAPSHOT"

libraryDependencies += "org.scalatest"     %% "scalatest"       % "3.0.0"           % Test
libraryDependencies += "org.scalacheck"    %% "scalacheck"      % "1.13.4"          % Test
libraryDependencies += "com.storm-enroute" %% "scalameter-core" % "0.8.2"           % Test

//scalacOptions += "-Ymacro-debug-lite"
scalacOptions ++= Seq("-deprecation", "-feature")
scalacOptions in (Compile, doc) ++= Seq("-diagrams", "-Ymacro-debug-lite")

publishMavenStyle := true
publishArtifact in Test := false
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra :=
  <url>http://github.com/ctongfei/poly-collection</url>
    <licenses>
      <license>
        <name>MIT</name>
        <url>http://opensource.org/licenses/MIT</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:ctongfei/poly-collection.git</url>
      <connection>scm:git:git@github.com:ctongfei/poly-collection.git</connection>
    </scm>
    <developers>
      <developer>
        <id>ctongfei</id>
        <name>Tongfei Chen</name>
        <url>http://tongfei.me/</url>
      </developer>
    </developers>

