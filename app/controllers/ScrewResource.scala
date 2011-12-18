package controllers

import play.api.mvc._
import models.Screw
import org.bson.types.ObjectId

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 16/12/11
 * Time: 23:10
 */

object ScrewResource extends Controller {

  def list = Action {
    Ok(Screw.count().toString)
  }

  def add = Action {
    Screw.insert(Screw(new ObjectId, "Ouh","hou","ou"))

    Ok("ok")
  }

  def delete = Action {NotImplemented}
}