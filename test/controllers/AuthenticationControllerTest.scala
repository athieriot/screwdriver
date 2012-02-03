package controllers

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import utils._
import org.specs2.mock.Mockito
import org.specs2.specification.Scope

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 23/01/12
 * Time: 23:06
 */

class AuthenticationControllerTest extends Specification with Mockito {

  trait mocks extends Scope {

    val FAKE_SESSION = "fakeSession"
    val FAKE_ACCESS_TOKEN = "fakeAccessToken"
    val FAKE_CODE = "fakeCode"

    lazy val gitHubUtilsSpy = mock[GitHubUtils]

    lazy val fakeRequestWithSession = FakeRequest().withSession((Authentication.GITHUB_TOKEN_SESSION, FAKE_SESSION))

    def fakeRequestAsGitHubResponse(code: String) = {
      FakeRequest("GET", "/?code=" + code)
    }

    def authenticationControllerWithMock() = {
      //TODO: Find a proper way to inject GitHubObject in Authentication Controller (implicit)
      //http://julien.richard-foy.fr/blog/2011/11/26/dependency-injection-in-scala-with-play-2-it-s-free/
      val controller = controllers.Authentication
      controller.setGitHubUtils(gitHubUtilsSpy)
      controller
    }
  }

  "Authentication Controller" should {

    "be able to extract the github token in session" in new mocks {
      running(FakeApplication()) {
        controllers.Authentication.extractToken(fakeRequestWithSession) must equalTo(Some(FAKE_SESSION))
      }
    }

    "redirect to Github if no session token is found" in {
      running(FakeApplication()) {
        //Given
        //When
        val result = controllers.Authentication.connect()(FakeRequest())

        //Then
        status(result) must equalTo(SEE_OTHER)
        redirectLocation(result).get must not beNull
      }
    }

    "redirect to Github if session token is not valid" in new mocks {
      running(FakeApplication()) {
        //Given
        gitHubUtilsSpy.testCall(anyString) returns false
        gitHubUtilsSpy.authorize(anyString) returns "something"

        //When
        val result = authenticationControllerWithMock().connect()(fakeRequestWithSession)

        //Then
        status(result) must equalTo(SEE_OTHER)
        redirectLocation(result).get must not beNull

        there was one(gitHubUtilsSpy).testCall(anyString)
      }
    }

    "return OK if already connected" in new mocks {
      running(FakeApplication()) {
        //Given
        gitHubUtilsSpy.testCall(anyString) returns true

        //When
        val result = authenticationControllerWithMock().connect()(fakeRequestWithSession)

        //Then
        status(result) must equalTo(OK)
        there was one(gitHubUtilsSpy).testCall(anyString)
      }
    }

    "intersept Github response and Unauthorize when code is not Valid" in new mocks {
      running(FakeApplication()) {
        //Given
        gitHubUtilsSpy.accessToken(anyString, anyString, anyString) returns FAKE_ACCESS_TOKEN
        gitHubUtilsSpy.testCall(anyString) returns false

        //When
        val result = authenticationControllerWithMock().authorized()(fakeRequestAsGitHubResponse(FAKE_CODE))

        //Then
        status(result) must equalTo(UNAUTHORIZED)
        there was one(gitHubUtilsSpy).testCall(anyString)
      }
    }

    "intersept Github response and Unauthorize when no code is provided" in new mocks {
      running(FakeApplication()) {
        //Given
        gitHubUtilsSpy.accessToken(anyString, anyString, anyString) returns FAKE_ACCESS_TOKEN
        gitHubUtilsSpy.testCall("") returns false

        //When
        val result = authenticationControllerWithMock().authorized()(FakeRequest())

        //Then
        status(result) must equalTo(UNAUTHORIZED)
        there was one(gitHubUtilsSpy).testCall(anyString)
      }
    }

    "intersept Github response and return OK if successfully connected" in new mocks {
      running(FakeApplication()) {
        //Given
        gitHubUtilsSpy.accessToken(anyString, anyString, anyString) returns FAKE_ACCESS_TOKEN
        gitHubUtilsSpy.testCall(anyString) returns true

        //When
        val result = authenticationControllerWithMock().authorized()(fakeRequestAsGitHubResponse(FAKE_CODE))

        //Then
        status(result) must equalTo(OK)
        there was one(gitHubUtilsSpy).testCall(anyString)
      }
    }
  }
}