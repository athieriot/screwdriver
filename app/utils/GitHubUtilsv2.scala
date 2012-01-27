package utils

import play.api.libs.json.JsValue
import play.api.libs.concurrent.Promise
import play.api.libs.WS

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/01/12
 * Time: 21:56
 */

object GitHubUtilsv2 {

  private val github_url = "http://github.com/api/"
  
  private val github_version = "v2/"
  private val github_result_format = "json/"

  private val github_repos_search = "repos/search/"
  private val github_users_search = "user/search/"

  private def gitHubBaseUrl = github_url + github_version + github_result_format
  
  def searchReposOnTerm(term: String): Promise[JsValue] = {
    WS.url(gitHubBaseUrl + github_repos_search + term).get().map(_.json)
  }

  def searchUsersOnTerm(term: String): Promise[JsValue] = {
    WS.url(gitHubBaseUrl + github_users_search + term).get().map(_.json)
  }
}