package controllers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import utils._
import org.mockito.Mockito._
import org.mockito.Matchers._

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 23/01/12
 * Time: 23:06
 */

object AuthenticationControllerTest extends Specification {

  val gitHubUtilsSpy = mock(classOf[GitHubUtils])

  lazy val fakeRequestWithSession = FakeRequest().withSession((Authentication.GITHUB_TOKEN_SESSION, "fakeSession"))

  "Authentication Controller" should {
    "can extract the github token in session" in {
      running(FakeApplication()) {
        controllers.Authentication.extractToken(fakeRequestWithSession) must equalTo(Some("fakeSession"))
      }
    }
    "redirect to Github if no session token is found" in {
      running(FakeApplication()) {
        val result = controllers.Authentication.connect()(FakeRequest())

        status(result) must equalTo(SEE_OTHER)
        redirectLocation(result).get must not beNull
      }
    }
    "redirect to Github if session token is not valid" in {
      running(FakeApplication()) {
        when(gitHubUtilsSpy.testCall(anyString())).thenReturn(false)
        when(gitHubUtilsSpy.authorize(anyString())).thenCallRealMethod()

        val result = authenticationControllerWithMock().connect()(fakeRequestWithSession)

        status(result) must equalTo(SEE_OTHER)
        redirectLocation(result).get must not beNull
      }
    }
    "return OK if already connected" in {
      running(FakeApplication()) {
        when(gitHubUtilsSpy.testCall(anyString())).thenReturn(true)

        val result = authenticationControllerWithMock().connect()(fakeRequestWithSession)

        status(result) must equalTo(OK)
      }
    }
    "intersept Github response and Unauthorize when code is not Valid" in {
      running(FakeApplication()) {
        when(gitHubUtilsSpy.accessToken(anyString(), anyString(), anyString())).thenReturn("fakeAccessToken")
        when(gitHubUtilsSpy.testCall(anyString())).thenReturn(false)

        val result = authenticationControllerWithMock().authorized()(FakeRequest("GET", "/?code=fakeCode"))

        status(result) must equalTo(UNAUTHORIZED)
      }
    }

    "intersept Github response and Unauthorize when no code is provided" in {
      running(FakeApplication()) {
        when(gitHubUtilsSpy.accessToken(anyString(), anyString(), anyString())).thenReturn("fakeAccessToken")
        when(gitHubUtilsSpy.testCall("")).thenReturn(false)

        val result = authenticationControllerWithMock().authorized()(FakeRequest())

        status(result) must equalTo(UNAUTHORIZED)
      }
    }

    "intersept Github response and return OK if successfully connected" in {
      running(FakeApplication()) {
        when(gitHubUtilsSpy.accessToken(anyString(), anyString(), anyString())).thenReturn("fakeAccessToken")
        when(gitHubUtilsSpy.testCall(anyString())).thenReturn(true)

        val result = authenticationControllerWithMock().authorized()(FakeRequest("GET", "/?code=fakeCode"))

        status(result) must equalTo(OK)
      }
    }
  }

  def authenticationControllerWithMock() = {
    //TODO: Find a proper way to inject GitHubObject in Authentication Controller (implicit)
    val controller = controllers.Authentication
    controller.setGitHubUtils(gitHubUtilsSpy)
    controller
  }
}