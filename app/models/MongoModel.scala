package models

import utils.DBMongo._
import com.mongodb.DBObject

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 18/12/11
 * Time: 10:13
 */

trait MongoModel[T] {

  def getCollectionName(): String

  implicit def Model2DBObject(x: T): DBObject

  def all() = {
    withConnection(getCollectionName()) { collection =>
      collection.find().toList
    }
  }

  def save(dbObject: DBObject) {
    withConnection(getCollectionName()) { collection =>
      collection.save(dbObject)
    }
  }
}