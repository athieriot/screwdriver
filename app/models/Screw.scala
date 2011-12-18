package models

import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.DBObject

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 17/12/11
 * Time: 16:47
 */

case class Screw (
  title: String,

  link: String,

  details: String
)

object Screw extends MongoModel[Screw] {

  def getCollectionName() = "screws"

  implicit def Model2DBObject(x: Screw) = {

    val builder = MongoDBObject.newBuilder

    builder += "title" -> x.title
    builder += "link" -> x.link
    builder += "details" -> x.details

    builder.result
  }
}