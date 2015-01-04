package exercises

/**
 * Created by aguestuser on 12/31/14.
 */
class Ch05_Classes {
  // *** CH 5 *** //

  // 1 //

  // Improve the Counter class in Section 1 so that it doesn't turn negative at Int.MaxValue.

  class Counter {
    private var value: Int = Int.MaxValue - 1
    def increment() = { if (value < Int.MaxValue) value += 1 }
    def current = value
    override def toString() = "Counter: " + value
  }

  // 2 //

  // Write a class BankAccount with methods deposit and withdraw, and a read-only property balance.

  // without constructor params

  class BankAccount {
    private var bal: Int = 0
    def balance = bal
    def deposit (amt: Int) = { bal += amt }
    def withdraw (amt: Int) = { bal -= amt }
  }

  // with constructor params

  class BankAccount_1(private var bal: Double){
    def balance = bal
    def deposit (amt: Double) = { bal += amt }
    def withdraw (amt: Double) = { bal -= amt }
  }

  // 3 //

  /* Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean that checks whether this time comes before the other. A Time object should be constructed as new Time(hrs, min), where hrs is in military time (between 0 and 23). */

  // long

  class Time(private var hrs: Int, private var min: Int ){
    def before(other: Time): Boolean = {
      if (hrs < other.hrs) true
      else if (hrs == other.hrs && min < other.min) true
      else false
    }
  }

  // shorter

  class Time_1(private val hrs: Int, private val min: Int ){
    def before(other: Time_1) =
      hrs < other.hrs || hrs == other.hrs && min < other.min
  }

  // 4 //

  /* Reimplement the Time class from the preceding exercise so that the internal representation is the number of minutes since midnight (between 0 and 24×60-1). Do not change the public interface. That is, client code should be unaffected by your change.*/


  class Time_2(val hrs: Int, val min: Int) {
    private val minSinceMid: Int = 60*hrs + min
    def before(other: Time_2) = minSinceMid < other.minSinceMid
  }

  // 5 //

  //Make a class Student with read-write JavaBeans properties name (of type String) and id (of type Long). What methods are generated? (Use javap to check.) Can you call the JavaBeans getters and setters in Scala? Should you?

  //class Student(@BeanProperty var name: String, @BeanProperty var id: Int)

  // 6 //

  // In the Person class of Section 1, provide a primary constructor that turns negative ages to 0.

  class Person(var age: Int) {
    age = if (age > 0) age else 0
  }

  // 7 //

  // Write a class Person with a primary constructor that accepts a string containing a first name, a space, and a last name, such as new Person("Fred Smith"). Supply read-only properties firstName and lastName. Should the primary constructor parameter be a var, a val, or a plain parameter? Why?

  class Person_1(fullName: String){
    val List(firstName, lastName) = """(\w+)""".r.findAllIn(fullName).toList.take(2)
  }

  // 8 //

  // Make a class Car with read-only properties for manufacturer, model name, and model year, and a read-write property for the license plate. Supply four constructors. All require the manufacturer and model name. Optionally, model year and license plate can also be specified in the constructor. If not, the model year is set to -1 and the license plate to the empty string. Which constructor are you choosing as the primary constructor? Why?

  class Car(
             val manufacturer: String,
             val model: String,
             val year: Int = -1,
             val licensePlate: String = ""
             )

  // 9 //

  // SORRY!!

  // 10 //

  /*

  Consider the class
    class Employee(val name: String, var salary: Double) {
      def this() { this("John Q. Public", 0.0) }
    }
  Rewrite it to use explicit fields and a default primary constructor. Which form do you prefer? Why?

  */

  class Employee(defName: String = "John Q. Public", defSalary: Double = 0.0){
    val name: String = defName
    val salary: Double = defSalary
  }

  // more succinctly:

  class Employee_1(val name: String = "John Q. Public", val salary: Double = 0.0)

}
