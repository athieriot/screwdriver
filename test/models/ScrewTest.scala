package models

import org.specs2.mutable.Specification
import play.api.test.MockApplication._
import org.specs2.specification.BeforeExample
import org.bson.types.ObjectId

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 18/12/11
 * Time: 20:17
 */

object ScrewTest extends Specification with BeforeExample {
  
  def before = {

    Screw.collection.dropCollection()

    Screw.insert(Screw(new ObjectId(), "title1", "link1", "details1"))
    Screw.insert(Screw(new ObjectId(), "title2", "link2", "details2"))
    Screw.insert(Screw(new ObjectId(), "title3", "link3", "details3"))
  }

  "Find some screws " should {
    "return 3 results" in {
      withApplication(Nil, Map.empty) {
        Screw.count() should be equalTo 3
      }
    }
  }
}