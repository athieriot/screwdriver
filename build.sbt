resolvers ++= Seq(                                                                                                                                        
   DefaultMavenRepository,
   Resolver.url("Play", url("http://download.playframework.org/ivy-releases/"))(Resolver.ivyStylePatterns),
   "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)
