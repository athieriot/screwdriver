import com.mongodb.Mongo
import de.flapdoodle.embedmongo.config.MongodConfig
import de.flapdoodle.embedmongo.distribution.Version
import de.flapdoodle.embedmongo.{MongoDBRuntime, MongodProcess, MongodExecutable}
import org.specs2.specification.{AfterExample, BeforeExample}
import play.api.test.FakeApplication

trait Connection extends BeforeExample with AfterExample {

  val FakeMongoApp = FakeApplication(additionalPlugins = Seq("se.radley.plugin.salat.SalatPlugin"))

  var _mongodExe: MongodExecutable = null
  var _mongod: MongodProcess = null

  def before() {
    val runtime: MongoDBRuntime = MongoDBRuntime. getDefaultInstance
    _mongodExe = runtime.prepare(new MongodConfig(Version.V2_1_1, 27017, true))
    _mongod = _mongodExe.start()
  }

  def after() {
    _mongod.stop()
    _mongodExe.cleanup()
  }
}
