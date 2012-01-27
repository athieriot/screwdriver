package controllers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import org.specs2.mock._
import utils.GitHubUtils

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 23/01/12
 * Time: 23:06
 */

object SecurityControllerTest extends Specification with Mockito {

  //var mockGitHubUtils = mock[java.util.List[String]]

  "Security" should {
    "redirect to Github if no session token is found" in {
      running(FakeApplication()) {
        val action = controllers.Security.connect()
        val result = action(FakeRequest())

        status(result) must equalTo(SEE_OTHER)
        redirectLocation(result).get must contain("github")
      }
    }
    "redirect to Github if session token is not ok" in {
      running(FakeApplication()) {
        //TODO: Find a proper way to inject GitHubObject in Security Controller
        //mockGitHubUtils.testCall("") returns false

        //val controller = controllers.Security
        //controller.setGitHubUtils(mockGitHubUtils)

        //val action = controller.connect()
        //FIXME: I NEED to set a session cookie in FakeRequest
        //val result = action(FakeRequest())

        //status(result) must equalTo(SEE_OTHER)
        //redirectLocation(result).get must contain("github")

        //there was one(mockGitHubUtils).testCall("")
      }
    }
  }
}