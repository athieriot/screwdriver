import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "screwdriver"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      "com.novus" %% "salat-core" % "0.0.8-SNAPSHOT",
      "org.mockito" % "mockito-all" % "1.9.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies).settings(defaultScalaSettings:_*).settings(
      // Add your own project settings here      
    )

}
