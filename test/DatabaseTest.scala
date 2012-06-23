import org.specs2.mutable.Specification
import play.api.test.Helpers._
import models._

class DatabaseTest extends Specification with EmbeddedConnection {

  "Database" should {
    "be able to save a Project" in {
      running(FakeMongoApp) {
        Project.save(Project(name = "test"))
        Project.count() must be greaterThan 0
      }
    }
  }
}