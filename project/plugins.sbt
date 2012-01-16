resolvers ++= Seq(
    DefaultMavenRepository,
    Resolver.url("Play", url("http://download.playframework.org/ivy-releases/"))(Resolver.ivyStylePatterns),
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    Resolver.url("ovh-repo", url("http://www.countyourcouzin.fr/local/"))(Resolver.ivyStylePatterns)
)

addSbtPlugin("play" % "sbt-plugin" % "2.0-RC1-SNAPSHOT")
addSbtPlugin("com.typesafe.startscript" % "xsbt-start-script-plugin" % "0.5.0")
