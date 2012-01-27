package utils

import org.specs2.mutable.Specification
import play.api.libs.concurrent.Promise
import play.api.libs.json.JsValue

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/01/12
 * Time: 21:56
 */

object GitHubUtilsv2Test extends Specification {

  "GitHubUtilsv2 search repo on infinitest" should {
    "return at least one result" in {
      GitHubUtilsv2.searchReposOnTerm("infinitest") should not beNull
    }
    "return a Json Promise" in {
      GitHubUtilsv2.searchReposOnTerm("infinitest") should beAnInstanceOf[Promise[JsValue]]
    }
  }

  "GitHubUtilsv2 search user on athieriot" should {
    "return at least one result" in {
      GitHubUtilsv2.searchUsersOnTerm("athieriot") should not beNull
    }
    "return a Json Promise" in {
      GitHubUtilsv2.searchUsersOnTerm("athieriot") should beAnInstanceOf[Promise[JsValue]]
    }
  }
}