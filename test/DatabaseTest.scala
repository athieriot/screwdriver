import models.Project
import org.specs2.mutable.Specification

import play.api.test._
import play.api.test.Helpers._

import com.mongodb.casbah.Imports._
import play.api._
import models._
import se.radley.plugin.salat._

class DatabaseTest extends Specification with Connection {

  "Database" should {
    "be able to save a Project" in {
      running(FakeMongoApp) {
        Project.save(Project(name = "test"))
        Project.count() must be greaterThan 0
      }
    }
  }
}