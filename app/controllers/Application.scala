package controllers

import play.api.mvc._

object Application extends Controller {

  def index() = Action {
    Ok(views.html.index())
  }

  def search = Action {
    Ok(views.html.index())
  }

  def about = Action {
    Ok(views.html.about())
  }

  def space = Action {
    Ok(views.html.space())
  }
}