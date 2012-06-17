package utils

import org.specs2.mutable.Specification
import play.api.libs.concurrent.Promise
import play.api.libs.json.JsValue
import play.api.test.Helpers._
import play.api.test.FakeApplication

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/01/12
 * Time: 21:56
 */

class GitHubUtilsv2Test extends Specification {

  "GitHubUtilsv2 search" should {

    "return at least one result for an infinitest repo" in {
      running(FakeApplication()) {
        GitHubUtilsv2.searchReposOnTerm("infinitest") should not beNull
      }
    }

    "return a Json Promise for a repo" in {
      running(FakeApplication()) {
        GitHubUtilsv2.searchReposOnTerm("infinitest") should beAnInstanceOf[Promise[JsValue]]
      }
    }

    "return at least one result for a athieriot user" in {
      running(FakeApplication()) {
        GitHubUtilsv2.searchUsersOnTerm("athieriot") should not beNull
      }
    }

    "return a Json Promise for a user" in {
      running(FakeApplication()) {
        GitHubUtilsv2.searchUsersOnTerm("athieriot") should beAnInstanceOf[Promise[JsValue]]
      }
    }
  }
}