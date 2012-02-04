package controllers

import play.api.Play.configuration
import play.api.Play.current
import play.api.mvc._
import utils.GitHubUtils


/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 22/01/12
 * Time: 15:55
 */

object Authentication extends Controller {

  var gitHubUtils: GitHubUtils = GitHubUtils()

  val GITHUB_TOKEN_SESSION = "token"

  val CLIENT_ID =  configuration.getString("github.client.id").getOrElse("")
  val CLIENT_SECRET =  configuration.getString("github.client.secret").getOrElse("")

  def connect() = Action { request =>
    val access_token = extractToken(request)

    if (access_token.isEmpty)
      askAuthorizations()
    else if (!isAccessTokenOk(access_token.get))
      askAuthorizations()
    else
      Ok("Already connected")
  }

  def extractToken(request: Request[AnyContent]): Option[String] = request.session.get(GITHUB_TOKEN_SESSION)

  //Call only on a return of Github OAuth(entification)
  def authorized() = Action { request =>
    val temporary_code = request.queryString.get("code").getOrElse(List("")).head
    val access_token = gitHubUtils.accessToken(CLIENT_ID, CLIENT_SECRET, temporary_code)

    if (isAccessTokenOk(access_token))
      //TODO: Do an Ajax callable function
      Ok(views.html.search.render())
        .withSession(new Session(Map(GITHUB_TOKEN_SESSION -> access_token)))
    else
      Unauthorized("Call to Github unauthorized")
  }

  def deconnect() = Action {
    Ok(views.html.search.render()).withNewSession
  }
  
  private def askAuthorizations(): SimpleResult[Results.EmptyContent] = {
    Redirect(gitHubUtils.authorize(CLIENT_ID))
  }

  private def isAccessTokenOk(access_token: String) = gitHubUtils.testCall(access_token)

  def setGitHubUtils(gitHubUtils: GitHubUtils) {
    this.gitHubUtils = gitHubUtils
  }
}