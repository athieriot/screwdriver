package controllers

import com.mongodb.casbah.Imports._

import play.api.Play.configuration
import play.api.Play.current

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 17/12/11
 * Time: 20:51
 */

object DBMongo {

  val host = configuration.getString("mongodb.host").getOrElse("localhost")
  val port = configuration.getInt("mongodb.port").getOrElse(27017)
  val base = "screwdriver"

  //TODO: Logs

  def getConnection(): MongoDB = MongoConnection(host, port)(base)

}