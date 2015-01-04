package exercises

import org.scalatest.FunSuite

/**
 * Created by aguestuser on 12/26/14.
 */
class ExampleTest extends FunSuite {
  test ("Example#sayHello returns a string"){
    val ex = new Example
    assert(ex.sayHello("Austin") == "Hello Austin")
  }
}
