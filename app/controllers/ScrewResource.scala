package controllers

import play.api.mvc._
import models.Screw

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 16/12/11
 * Time: 23:10
 */

object ScrewResource extends Controller {

  def list = Action {
    Ok(Screw.all().toString)
  }

  def add = Action {
    Screw.save(Screw("Ouh","hou","ou"))

    Ok("ok")
  }

  def delete = Action {NotImplemented}
}