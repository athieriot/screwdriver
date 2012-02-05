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

  val GITHUB_TOKEN_SESSION = "token"

  val CLIENT_ID =  configuration.getString("github.client.id").getOrElse("")
  val CLIENT_SECRET =  configuration.getString("github.client.secret").getOrElse("")

  //TODO: Find a proper way to inject GitHubObject in Authentication Controller (implicit)
  var gitHubUtils: GitHubUtils = GitHubUtils()

  def setGitHubUtils(gitHubUtils: GitHubUtils) {this.gitHubUtils = gitHubUtils}

  //Useful when another action need authentication
  //TODO: return access_token would be a good idea
  //TODO: Even better if that can remember original address
  //TODO: Do an Ajax callable function
  def Authenticated[A](action: Action[A]): Action[A] = {
    Action(action.parser) { request =>
      if (!amIConnected(request))
        askAuthorizations()
      else
        action(request)
    }
  }

  def connect() = Action { request =>
    if (!amIConnected(request))
      askAuthorizations()
    else
      Ok(views.html.index())
  }

  //Called only on a return of Github OAuth
  def authorized() = Action { request =>
    val temporary_code = request.queryString.get("code").getOrElse(List("")).head
    val access_token = gitHubUtils.accessToken(CLIENT_ID, CLIENT_SECRET, temporary_code)

    if (isAccessTokenOk(access_token))
      Ok(views.html.index())
        .withSession(new Session(Map(GITHUB_TOKEN_SESSION -> access_token)))
    else
      Unauthorized(views.html.unauthorized())
  }

  def deconnect() = Action {
    Ok(views.html.index()).withNewSession
  }

  def extractToken[A](request: Request[A]): Option[String] = request.session.get(GITHUB_TOKEN_SESSION)

  private def amIConnected[A](request: Request[A]) = {
    val access_token = extractToken(request)
    access_token.isDefined && isAccessTokenOk(access_token.get)
  }

  private def askAuthorizations(): SimpleResult[Results.EmptyContent] = {
    Redirect(gitHubUtils.authorize(CLIENT_ID))
  }

  private def isAccessTokenOk(access_token: String) = gitHubUtils.testCall(access_token)
}