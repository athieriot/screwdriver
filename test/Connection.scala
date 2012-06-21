import de.flapdoodle.embedmongo._
import config.MongodConfig
import distribution.Version
import org.specs2.specification.{AfterExample, BeforeExample}
import play.api.test.FakeApplication

trait Connection extends BeforeExample with AfterExample {

  val port: Int = 12345
  val FakeMongoApp = FakeApplication(additionalConfiguration = Map("mongodb.default.port" -> port.toString),
                                     additionalPlugins = Seq("se.radley.plugin.salat.SalatPlugin"))

  var _mongodExe: MongodExecutable = null
  var _mongod: MongodProcess = null

  def before() {
    val runtime: MongoDBRuntime = MongoDBRuntime. getDefaultInstance
    _mongodExe = runtime.prepare(new MongodConfig(Version.V2_1_1, port, true))
    _mongod = _mongodExe.start()
  }

  def after() {
    _mongod.stop()
    _mongodExe.cleanup()
  }
}
