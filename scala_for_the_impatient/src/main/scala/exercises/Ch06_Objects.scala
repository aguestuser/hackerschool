package exercises

/**
 * Created by aguestuser on 12/31/14.
 */

// CH 6 //

class Chapter_6_Objects {

  // 1 //
  /* Write an object Conversions with methods inchesToCentimeter, gallonsToLiter, and milesToKilometer.
   */

  object Conversions {
    def inchesToCentimeter(in: Double) : Double = in * 2.540
    def gallonsToLiter(gal: Double) : Double = gal * 3.785
    def milesToKilometer(mi: Double) : Double = mi * 1.609
  }

  //2//
  /*The preceding problem wasn't very object-oriented. Provide a general superclass UnitConversion and define objects InchesToCentimeter, GallonsToLiter, and MilesToKilometer that extend it.*/

  abstract class UnitConversion {
    val k : Double
    def convert(input: Double) : Double = input*k
  }

  object InchesToCentimeter extends UnitConversion {
    val k = 2.540
  }

  object GallonsToLiter extends UnitConversion {
    val k = 3.785
  }

  object MilesToKilometer extends UnitConversion {
    val k = 1.609
  }

  //3//
  /*Define an Origin object that extends java.awt.Point. Why is this not actually a good idea? (Have a close look at the methods of the Point class.)*/

  object Origin extends java.awt.Point{
    x = 0; y = 0
  }

  // no idea why this wouldn't be good!

  //4//
  /*Define a Point class with a companion object so that you can construct Point instances as Point(3, 4), without using new.*/

  class Point(val x: Int, val y: Int){
    override def toString() = "(%d, %d)".format(x, y)
  }

  object Point{
    def apply(x: Int, y: Int) : Point = new Point(x,y)
  }

  //5//
  /*Write a Scala application, using the App trait, that prints the command-line arguments in reverse order, separated by spaces. For example, scala Reverse Hello World should print World Hello.*/

  /* See `Reverse` object below ---v */

  //6//
  /*Write an enumeration describing the four playing card suits so that the toString method returns ♣, ♦, ♥, ♠.*/

  object Suit extends Enumeration {
    type Suit = Value
    val CLUB = Value("♣")
    val DIAMOND = Value("♦")
    val HEART = Value("♥")
    val SPADE = Value("♠")
    override def toString() = this.values.mkString(",")
  }


  //7//
  /*Implement a function that checks whether a card suit value from the preceding exercise is red.*/

  def isRed(suit: Suit.Value) : Boolean = List(Suit.DIAMOND, Suit.HEART) contains suit


  import Suit._
  def altIsRed(suit: Suit) : Boolean = List(DIAMOND, HEART) contains suit

  //8//
  /*Write an enumeration describing the eight corners of the RGB color cube. As IDs, use the color values (for example, 0xff0000 for Red).*/

  object rgbCorner extends Enumeration {
    type rgbCorner = Value
    val BLACK = Value(0x000000)
    val BLUE = Value(0x0000ff)
    val RED = Value(0xff0000)
    val MAGENTA = Value(0xff00ff)
    val GREEN = Value(0x00ff00)
    val CYAN = Value(0x00ffff)
    val YELLOW = Value(0xffff00)
    val WHITE = Value(0xffffff)
  }

}

object Reverse extends App {
  println (args.reverse.mkString(" "))
}

/* to run the above, either:
  (1)
    (a) navigate to `<root>/target/scala-2.11/classes`
    (b) run `scala exercises.Reverse hello world whats up`
  (2)
    (a) navigate to `<root>/src/scala/exercises`
    (b) run `scalac Reverse.scala` (to compile?)
    (c) run `scala exercises.Reverse hello world whats up` to run
*/