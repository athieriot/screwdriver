package models

import org.bson.types.ObjectId
import utils.DBMongo
import models.play_salat_context._

import com.novus.salat.dao._

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 17/12/11
 * Time: 16:47
 */

case class Screw (
  _id: ObjectId = new ObjectId,

  title: String,

  link: String,

  details: String
)

object Screw extends SalatDAO[Screw, ObjectId](collection = DBMongo.getConnection()("screws"))