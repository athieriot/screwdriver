package controllers

import play.api.mvc._
import models.Screw
import org.bson.types.ObjectId
import utils.GitHubUtils

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 16/12/11
 * Time: 23:10
 */

object DriverResource extends Controller {

  def search(term: String) = Action {
    Async(GitHubUtils.searchUsersOnTerm(term).map(Ok(_)))
  }
}