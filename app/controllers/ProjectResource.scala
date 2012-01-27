package controllers

import play.api.mvc._
import utils.GitHubUtilsv2

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
}