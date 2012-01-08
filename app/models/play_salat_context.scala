package models

import com.novus.salat._
import play.Play


/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 18/12/11
 * Time: 18:51
 */

package object play_salat_context {

  /**
   * Here is where we define the custom Play serialization context, including the Play classloader.
   */
  implicit val ctx = {
    val c = new Context {
      val name = "play-context"
    }

    c.registerClassLoader(Play.application().classloader)

    c
  }
}