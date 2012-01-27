package controllers

import play.api.mvc._
import utils.{GitHubUtils, GitHubUtilsv2}

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 16/12/11
 * Time: 23:10
 */

object DriverResource extends Controller {

  def search(term: String) = Action {
    Async(GitHubUtilsv2.searchUsersOnTerm(term).map(Ok(_)))
  }

  //TODO: Do a block Action "Authentication"
  def user() = Action { request =>
    Async(GitHubUtils.user(controllers.Security.extractToken(request).getOrElse("")).map(Ok(_)))
  }
}