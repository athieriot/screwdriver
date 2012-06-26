package controllers

import play.api._
import libs.json.Json
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }

  //FIXME: Delete this API when a true one will be available for frisby testing
  def message = Action {
    Ok(Json.toJson(Map("message" -> ("Hello Kitty"))))
  }
}