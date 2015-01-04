package exercises

/**
 * Created by aguestuser on 1/1/15.
 */
package object random {
  private var curInt : Int = 0
  val a = 1664525
  val b = 1013904223
  val n = 32
  val mod_factor = BigInt(2).pow(n)

  def setSeed(seed: Int) { curInt = seed }
  def nextInt() : Int = { curInt = (curInt * a + b % mod_factor).toInt; curInt }
  def nextDouble() : Double = { nextInt().toDouble }
}