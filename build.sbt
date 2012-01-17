resolvers ++= Seq(                                                                                                                                        
   DefaultMavenRepository,
   Resolver.url("Play", url("http://download.playframework.org/ivy-releases/"))(Resolver.ivyStylePatterns),
   "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
   "sbt-idea-ovh-repo" at "http://www.countyourcouzin.fr/maven/",
   Resolver.url("ovh-repo", url("http://www.countyourcouzin.fr/local/"))(Resolver.ivyStylePatterns)
)
