package controllers

import org.specs2.mutable._
import com.mongodb.casbah.MongoDB
import play.api.test.MockApplication

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 17/12/11
 * Time: 21:56
 */
object DBMongoTest extends Specification {

  "a DBMongo" should {
    "return a connection" in {
      MockApplication.withApplication(Nil, Map.empty) {
        DBMongo.getConnection() should anInstanceOf[MongoDB]
      }
    }
  }
}