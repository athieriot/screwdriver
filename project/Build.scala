import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "screwdriver"
    val appVersion      = "0.1-SNAPSHOT"

    val appDependencies = Seq(
      /*Database access*/
      "se.radley" %% "play-plugins-salat" % "1.0.4",

      /*Test*/
      "de.flapdoodle.embedmongo" % "de.flapdoodle.embedmongo" % "1.16" % "test",
      //Only to activate the Specs2 mock feature
      "org.mockito" % "mockito-all" % "1.9.0" % "test"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      routesImport += "se.radley.plugin.salat.Binders._",
      templatesImport += "org.bson.types.ObjectId"
    )

}
