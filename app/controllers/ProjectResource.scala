package controllers

import play.api.mvc._
import controllers.Authentication._
import utils.GitHubUtils._
import utils.{GitHubUtils, GitHubUtilsv2}

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 16/12/11
 * Time: 23:10
 */

object ProjectResource extends Controller {

  def search(term: String) = Action {
    Async(GitHubUtilsv2.searchReposOnTerm(term).map(Ok(_)))
  }

  def userRepos() = Authenticated {
    Action { request =>
      Async(GitHubUtils().userRepos(Authentication.extractToken(request).getOrElse("")).map(Ok(_)))
    }
  }
}