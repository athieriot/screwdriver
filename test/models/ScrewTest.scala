package models

import org.specs2.mutable.Specification
import org.specs2.specification.BeforeExample
import org.bson.types.ObjectId
import com.mongodb.casbah.commons.MongoDBObject
import play.api.test.FakeApplication
import play.api.test.Helpers._

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 18/12/11
 * Time: 20:17
 */

object ScrewTest extends Specification {
/*
  //with BeforeExample

  val screw1 = Screw(new ObjectId(), "title1", "link1", "details1")
  val screw2 = Screw(new ObjectId(), "title2", "link2", "details2")
  val screw3 = Screw(new ObjectId(), "title3", "link3", "details3")

  def before = {

    Screw.collection.dropCollection()

    Screw.insert(screw1)
    Screw.insert(screw2)
    Screw.insert(screw3)
  }

  "Search screws" should {
    "return 3 results" in {
      running(FakeApplication()) {
        Screw.count() should be equalTo 3
      }
    }
    "return a list of Screws" in {
      running(FakeApplication()) {
        Screw.find(ref = MongoDBObject())
          .toList should anInstanceOf[List[Screw]]
      }
    }
    "at first result, a screw with title1 as title" in {
      running(FakeApplication()) {
        Screw.find(ref = MongoDBObject())
          .toList.head.title shouldEqual "title1"
      }
    }
  }

  "Remove a screw" should {
    "return only 2 results" in {
      running(FakeApplication()) {
        Screw.remove(screw1)
        Screw.count() should be equalTo 2
      }
    }
    "at first result, a screw with title2 as title" in {
      running(FakeApplication()) {
        Screw.find(ref = MongoDBObject())
          .toList.head.title shouldEqual "title2"
      }
    }
  }
  */
}