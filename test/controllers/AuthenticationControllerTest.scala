package controllers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import utils._
import org.specs2.mock.Mockito

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 23/01/12
 * Time: 23:06
 */

object AuthenticationControllerTest extends Specification with Mockito {

  val gitHubUtilsMock = spy(GitHubUtils());

  "Authentication" should {
    "redirect to Github if no session token is found" in {
      running(FakeApplication()) {
        val action = controllers.Authentication.connect()
        val result = action(FakeRequest())

        status(result) must equalTo(SEE_OTHER)
        redirectLocation(result).get must contain("github")
      }
    }
    "redirect to Github if session token is not ok" in {
      running(FakeApplication()) {
        //Given
        //TODO: Find a proper way to inject GitHubObject in Authentication Controller (implicit)
        gitHubUtilsMock.testCall("") returns false

        val controller = controllers.Authentication
        controller.setGitHubUtils(gitHubUtilsMock)
        val playRequest = FakeRequest().withHeaders(("Cookie", "PLAY_SESSION="))

        //When
        val result = controller.connect()(playRequest)

        //Then
        status(result) must equalTo(SEE_OTHER)
        redirectLocation(result).get must contain("github")
        there was gitHubUtilsMock.testCall("")
      }
    }
  }
}