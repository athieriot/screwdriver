resolvers ++= Seq(
    DefaultMavenRepository,
    Resolver.url("Play", url("http://download.playframework.org/ivy-releases/"))(Resolver.ivyStylePatterns),
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

addSbtPlugin("play" % "sbt-plugin" % "2.0.2-RC1")

addSbtPlugin("com.typesafe.startscript" % "xsbt-start-script-plugin" % "0.5.1")
