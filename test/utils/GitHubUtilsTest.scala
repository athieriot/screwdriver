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

object GitHubUtilsTest extends Specification {

  "GitHubUtils search repo on infinitest" should {
    "return at least one result" in {
      GitHubUtils.searchReposOnTerm("infinitest") should not beNull
    }
    "return a Json Promise" in {
      GitHubUtils.searchReposOnTerm("infinitest") should beAnInstanceOf[Promise[JsValue]]
    }
  }

  "GitHubUtils search user on athieriot" should {
    "return at least one result" in {
      GitHubUtils.searchUsersOnTerm("athieriot") should not beNull
    }
    "return a Json Promise" in {
      GitHubUtils.searchUsersOnTerm("athieriot") should beAnInstanceOf[Promise[JsValue]]
    }
  }
}