import com.github.athieriot.EmbedConnection
import org.specs2.mutable.Specification
import play.api.test.FakeApplication
import play.api.test.Helpers._
import models._

class DatabaseTest extends Specification with EmbedConnection {

  val port: Int = 12345
  val FakeMongoApp = FakeApplication(additionalConfiguration = Map("mongodb.default.port" -> port.toString),
                                     additionalPlugins = Seq("se.radley.plugin.salat.SalatPlugin"))

  "Database" should {
    "be able to save a Project" in {
      running(FakeMongoApp) {
        Project.save(Project(name = "test"))
        Project.count() must be greaterThan 0
      }
    }
  }
}