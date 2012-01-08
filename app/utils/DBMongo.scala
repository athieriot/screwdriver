package utils

import com.mongodb.casbah.Imports._

import play.api.Play.configuration
import play.api.Play.current
import play.api.Logger

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 17/12/11
 * Time: 20:51
 */

object DBMongo {

  val base = "screwdriver"
  val host = configuration.getString("mongodb.host").getOrElse("127.0.0.1")
  val port = configuration.getInt("mongodb.port").getOrElse(27017)

  def getConnection() = {
    Logger.info("Connection to mongodb instance at " + host + ":" + port + " and to base " + base)
    MongoConnection(host, port)(base)
  }
  
  def withConnection[A](collection: String)(block: MongoCollection => A): A = {
    Logger.info("Access to the " + collection + " collection")
    block(getConnection()(collection))
  }
}