package controllers

import play.api.mvc._
import play.libs.Json

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 16/12/11
 * Time: 23:10
 */

object ScrewResource extends Controller {

  private val ScrewCollection = DBMongo.getConnection()("screws")

  def list = Action {
    Ok(ScrewCollection.find().toList.toString)
  }

  def add = Action {NotImplemented}

  def delete = Action {NotImplemented}

}