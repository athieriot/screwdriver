package models

import play.api.Play.current
import java.util.{Date}
import com.novus.salat._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._

case class Project(
  id: ObjectId = new ObjectId,
  name: String
)

object Project extends ModelCompanion[Project, ObjectId] {
  val collection = mongoCollection("projects")
  val dao = new SalatDAO[Project, ObjectId](collection = collection) {}
}