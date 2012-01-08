package utils

import org.specs2.mutable._
import play.api.test.FakeApplication
import play.api.test.Helpers._
import utils.DBMongo._
import com.mongodb.casbah.{MongoCollection, MongoDB}

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 17/12/11
 * Time: 21:56
 */
object DBMongoTest extends Specification {
/*
  "DBMongo getConnection" should {
    "return a connection" in {
      running(FakeApplication()) {
        getConnection() should anInstanceOf[MongoDB]
      }
    }
  }

  "DBMongo withConnection" should {
    "call a block with a collection" in {
      running(FakeApplication()) {
        withConnection("screws") { connection =>
          connection should anInstanceOf[MongoCollection]
        }
      }
    }
  }
*/
}