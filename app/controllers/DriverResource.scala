package controllers

import play.api.mvc._
import utils.{GitHubUtils, GitHubUtilsv2}
import controllers.Authentication._

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

  def user() = Authenticated {
    Action { request =>
      Async(GitHubUtils().user(Authentication.extractToken(request).getOrElse("")).map(Ok(_)))
    }
  }
}