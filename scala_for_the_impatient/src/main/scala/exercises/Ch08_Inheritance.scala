package exercises

/**
 * Created by aguestuser on 1/2/15.
 */
class Ch08_Inheritance {
//  1.
/* Extend the following BankAccount class to a CheckingAccount class that charges $1 for every deposit and withdrawal.
*/
  class BankAccount(initBalance: Double) {
    protected var balance = initBalance

    def applyCommission(){ balance -= 1 }

    def deposit(amount: Double) : Double = { balance += amount; balance }
    def withdraw(amount: Double) : Double = { balance -= amount; balance }
  }

  class CheckingAccount(initBalance: Double)
    extends BankAccount(initBalance){

    override def deposit(amount: Double) : Double = {
      applyCommission()
      super.deposit(amount)
      balance
    }
    override def withdraw(amount: Double) : Double = {
      applyCommission()
      super.withdraw(amount)
      balance
    }
  }


//  2. Extend the BankAccount class of the preceding exercise to a class SavingsAccount that earns interest every month (when a method earnMonthlyInterest is called) and that has three free deposits or withdrawals every month. Reset the transaction count in the earnMonthlyInterest method.

  class SavingsAccount(initBalance: Double)
    extends CheckingAccount(initBalance) {

    private val rate = .05
    private val chargeThreshold = 3
    private var transactionCount = 0

    override def applyCommission(): Unit ={
      if (transactionCount > chargeThreshold) balance -= 1
      transactionCount += 1
    }

    def earnMonthlyInterest : Double = {
      transactionCount = 0
      balance += balance * rate
      balance
    }
  }

  class LongerSavingsAccount(initBalance: Double)
  extends BankAccount(initBalance){

    private val rate = .05
    private val chargeThreshold = 3
    private var transactionCount = 0

    def chargeIfApplicable(): Unit ={
      transactionCount += 1
      if (transactionCount > chargeThreshold) applyCommission()
    }

    def earnMonthlyInterest : Double = {
      transactionCount = 0
      balance += balance * rate
      balance
    }

    override def deposit(amt: Double) : Double = {
      chargeIfApplicable()
      super.deposit(amt)
    }
    override def withdraw(amt: Double) : Double = {
      chargeIfApplicable()
      super.withdraw(amt)
    }
  }


//  3.
// Consult your favorite Java or C++ textbook that is sure to have an example of a toy inheritance hierarchy, perhaps involving employees, pets, graphical shapes, or the like. Implement the example in Scala.

  // Sorry!

//  4.
// Define an abstract class Item with methods price and description. A SimpleItem is an item whose price and description are specified in the constructor. Take advantage of the fact that a val can override a def. A Bundle is an item that contains other items. Its price is the sum of the prices in the bundle. Also provide a mechanism for adding items to the bundle and a suitable description method.

  abstract class Item {
    def price : Double
    def description : String
  }

  class SimpleItem(val price: Double, val description: String)
  extends Item


//  5. Design a class Point whose x- and y-coordinate values can be provided in a constructor. Provide a subclass LabeledPoint whose constructor takes a label value and x- and y-coordinates, such as
//    new LabeledPoint("Black Thursday", 1929, 230.07)

  class Point(val x: Double, val y: Double)

  class LabeledPoint(label: String, x: Double, y: Double)
  extends Point(x,y)


//  6. Define an abstract class Shape with an abstract method centerPoint and subclasses Rectangle and Circle. Provide appropriate constructors for the subclasses and override the centerPoint method in each subclass.

  abstract class Shape {
    def centerPoint : Point
  }

  class Rectangle(ul: Point, lr: Point) extends Shape {
    def centerPoint = new Point((ul.x + lr.x) / 2, (ul.y + lr.y) / 2)
  }

  class Circle(l: Point, r: Point) extends Shape {
    def centerPoint = new Point((l.x + r.x) / 2, (l.y + r.y) / 2)
  }


//  7. Provide a class Square that extends java.awt.Rectangle and has three constructors, one that constructs a square with a given corner point and width, one that constructs a square with corner (0, 0) and a given width, and one that constructs a square with corner (0, 0) and width 0.

  // maybe...

//  8. Compile the Person and SecretAgent classes in Section 6 and analyze the class files with javap. How many name fields are there? How many name getter methods are there? What do they get? (Hint: Use the -c and -private options.)

  class Person(val name: String) {
    override def toString = getClass.getName + "[name=" + name + "]"
  }
  class SecretAgent(codename: String) extends Person(codename) {
    override val name = "secret"
    override val toString = "secret" // ...or class name
  }

  /*
  * Person has 1 name field: a public getter field
  * Secret agent has 2 name fields: a private value and a public getter
  * */

//  9. In the Creature class of Section 10, replace val range with a def. What happens when you also use a def in the Ant subclass? What happens when you use a val in the subclass? Why?

  class Creature {
    def range: Int = 10
    val env: Array[Int] = new Array[Int](range)
  }

  class Ant extends Creature {
    override def range: Int = 2
  }

  /*in this example there is no need for early definition, because the overriden `range` method is incorporated into `Creature`'s call to `env` (not sure why, but seems nice!*/

  class OtherAnt extends Creature {
    override val range: Int = 2
  }

  /* in this example the overriden `range` is NOT incorporated into the call to `env` and we wind up with an ant object with `env` set to an empty array (length 0), instead of an array with two empty elements in it. moral of the story: if you think a subclass might override a value used in a superclass method, store that value as a method instead of a field!*/

//    10. The file scala/collection/immutable/Stack.scala contains the definition
//  class Stack[A] protected (protected val elems: List[A])
//  Explain the meanings of the protected keywords. (Hint: Look at the discussion of private constructors in the “Classes” chapter.)
}
