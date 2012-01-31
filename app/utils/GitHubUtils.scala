package utils

import play.api.libs.concurrent.Promise
import play.api.libs.json._
import play.api.libs.ws.WS

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/01/12
 * Time: 21:56
 */

class GitHubUtils {

  private val github_api_url = "https://api.github.com/"
  private val github_url = "https://github.com/"

  private val github_oauth = "login/oauth/"

  private val github_authorize = "authorize"
  private val github_access_token = "access_token"
  private val github_user = "user"

  def authorize(clientId: String): String = github_url + github_oauth + github_authorize + "?client_id=" + clientId

  def accessToken(client_id: String, client_secret: String, code: String): String = {
    val token = WS.url(github_url + github_oauth + github_access_token)
      .withQueryString(("client_id", client_id))
      .withQueryString(("client_secret", client_secret))
      .withQueryString(("code", code))
      .get()
      .value.get.body

    val BodyPattern = "access_token=(.*)&token_type=(.*)".r
    val BodyPattern(access_token, token_type) = token

    return access_token;
  }

  def testCall(access_token: String): Boolean = {
    ((user(access_token).value.get) \ "login") != null
  }
  
  def user(access_token: String): Promise[JsValue] = {
    WS.url(github_api_url + github_user)
      .withHeaders(("Authorization", "token " + access_token))
      .get()
      .map(_.json)
  }
}

object GitHubUtils {
  def apply() = new GitHubUtils
}