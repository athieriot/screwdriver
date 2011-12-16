package controllers

import play.api._
import play.api.mvc._
import play.api.WS.HttpPromise

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def search(value: String) = Action {

    val httpPromise = WS.url("https://api.github.com/users/athieriot/gists").get

    AsyncResult(httpPromise.map(r => Ok(r.getResponseBody)))
  }
  
}