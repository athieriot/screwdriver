package utils

import org.specs2.mutable.Specification

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/01/12
 * Time: 21:56
 */

object GitHubUtilsTest extends Specification {

  "GitHubUtils request authorization URI" should {
    "return an url with client_id as argument" in {
      GitHubUtils().authorize("blabla") must contain("client_id=blabla")
    }
  }
}