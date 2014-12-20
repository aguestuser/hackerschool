// CH 2



//1// 

//one way:

def signum(num: Int) : Int = {
  if (num < 0)
    -1
  else if (num > 0)
    1
  else
    0
}

//another way:

def signum(num: Int) = 
  if (num < 0)
    -1 else if (num > 0)
    1 else
    0

def signum(num: Int) = {
  if (num <0)
    -1
  else if (num > 0)
    1
  else
    0
}

//4//
for (i <- 10 to 0 by -1){ println(i) }

// 5 //

//with loop:

def countdown(n: Int) {
  for (n <- n to 0 by -1) { println(n) }
}

def countdown(n: Int) : Int = {
  if (n == 0){
    println(0)
    n
  }    
  else {
    println(n)
    countdown(n-1)
  }
}

// 6 //

// with loop

def unicode_prod(s: String) : Int = {
  var prod = 1
  for (i <- 0 until s.length) prod = prod * s.charAt(i).toInt 
  prod
}

// with recursion

def uni_prod(s: String) : Int = {
  if (s == "") 1
  else s.charAt(0) * uni_prod(s.tail)
}

//alt syntax:

def uni_prod(s: String) : Int =
  if (s == "")
    1 else s.charAt(0) * uni_prod(s.tail)

// 7 //

def uni_prod(s: String) : Int =
  s.foldLeft(1)((a,b) => a*b)

// 8 & 9 //

see above!

// 10 //

def to_nth(x:Int, n: Int) : Int = {
  if (n == 0) 1
  else if (n < 0)  1 / to_nth(x, -n)
  else if (n > 0 & n % 2 == 0) to_nth(x, n/2) * to_nth(x, n/2)
  else x * to_nth(x, n-1)
}

//*** CH 3 ***//

//1//

// imperative
def make_array(n: Int) : Array[Int] = {
  for (item <- new Array[Int](n)) yield Random.nextInt(n)
}

// functional
def make_array(n: Int) : Array[Int] = {
  new Array[Int](n) map { (_: Int) => Random.nextInt(n) }
}

//3//

// with loop //

def swap(arr: Array[Int]) : Array[Int] = {
  (for (i <- 0 until arr.length) yield
    if (i % 2 == 0)
      if (i == arr.size - 1) arr(i)
      else arr(i + 1)
    else arr(i - 1)
  ).toArray
}

// with map

def swap(arr: Array[Int]) : Array[Int] = {
  arr.zipWithIndex.map{ case(_, i) =>
    if (i % 2 == 0)
      if (i == arr.size - 1) arr(i)
      else arr(i + 1)
    else arr(i - 1)
  }
}

// 4 //

def sort_by_sign(a: Array[Int]) : Array[Int] = {
  a.filter(_ > 0) ++ a.filter(_ <= 0)
}

// 5 //

// FOLD for averaging

// terse (but confusing?)
def avg(a: Array[Double]) : Double = {
  (0.0 /: a)(_ + _) / a.length
}

//verbose (but less confusing?)
def avg(a: Array[Double]) : Double = {
  a.foldLeft(0.0)(_ + _) / a.length
}

// 6 //
def rev_sort(a: Array[Int]) : Array[Int] = {
  a.sortWith(_ > _)
}

// 7 //

println(Array(1,1,1,2,3).distinct.toList)
println((ArrayBuffer() ++= Array(1,1,1,1,2,3).distinct).toString)

// 8 //

// PROBLEM: Given a sequence of integers, we want to remove all but the first negative number. 
// NOTE: below solution works, but seems extremely inelegant and probably slow!

def rem_tail_negs(a: Array[Int]) : Array[Int] = {
  val indices = a.zipWithIndex.filter { _._1 < 0 }.map { _._2 }.drop(1)
  a.zipWithIndex.filter { e => !indices.contains(e._2) }.map{ _._1 }
}

//*** CH 4 ***//

//1//


// PROBLEM: Set up a map of prices for a number of gizmos that you covet. Then produce a second map with the same keys and the prices at a 10 percent discount.

val prices = Map("charger" -> 50.00, "boots" -> 100.00, "laptop" -> 1000.00 )
val sale_prices = prices.mapValues( x => .9 * x)
val mod_name_sale_prices = prices.map{ case(k,v) => ("discounted " + k, .9 * v )}

// 2 //

// PROBLEM: Write a program that reads words from a file. Use a mutable map to count how often each word appears. To read the words, simply use a java.util.Scanner:

// skip to 3

//3//

// Repeat the preceding exercise with an immutable map.

// VICTORY!! I CAN DO A REDUCE IN SCALA!!!

def print_counts(map: Map[String, Int]) : Unit = {
  //for ((k,v) <- map) println(k+ ": " +v)
  map.map{ case(k,v) => println(k+ ": " + v) }
}

def count_words(path: String) : Map[String, Int] = {
  scala.io.Source.fromFile(path)
    .mkString
    .split("""(\.?\s+|\.)""")
    .foldLeft(Map[String, Int]())((b,a)
      => b + (a -> (b.getOrElse(a, 0) + 1)))
}

print_counts(count_words("../sample_data/lorem_ipsum.txt"))

// 4 //

// Repeat the preceding exercise with a sorted map, so that the words are printed in sorted order.

// question as asked

import scala.collection.immutable.SortedMap

def count_sort_words(path: String) : SortedMap[String, Int] = {
  scala.io.Source.fromFile(path)
    .mkString
    .split("""(\.?\s+|\.)""")
    .foldLeft(SortedMap[String, Int]())((b,a)
      => b + (a -> (b.getOrElse(a, 0) + 1)))
}

print_counts(count_sort_words("../sample_data/lorem_ipsum.txt"))

// soring by values instead of keys

import scala.collection.immutable.ListMap

def count_sort_counts(path: String) : ListMap[String,Int]  = {
  scala.collection.immutable.ListMap(
    scala.io.Source.fromFile(path)
    .mkString
    .split("""(\.?\s+|\.)""")
    .foldLeft(Map[String, Int]())((b,a)
      => b + (a -> (b.getOrElse(a, 0) + 1)))
    .toSeq.sortWith(_._2 > _._2):_*
  )
}

print_counts(count_sort_counts("../sample_data/lorem_ipsum.txt"))

// a couple tries at a recursive print loop

def print_counts_rec(maybeMap: Option[ListMap[String,Int]]) : Option[ListMap[String,Int]] = {
  maybeMap match {
    case None =>
      None
    case Some(map) =>
      println( map.head._1 +": "+ map.head._2)
      print_counts_rec(Option(map.tail))
  }
}

def print_counts_rec(map: ListMap[String,Int]) : Unit = {
  if (map.isEmpty)
    ListMap()
  else 
    println(map.head._1 +": "+ map.head._2)
    print_counts_rec(map.tail)
}

def print_counts_rec(map: ListMap[String,Int]) : Unit = map match {
  case ListMap[String,Int]() => ListMap[String,Int]()
  case (k,v) :: tail =>
    println(k+": "+v)
    print_counts_rec(tail)
}

print_counts_rec(count_sort_counts("../sample_data/lorem_ipsum.txt"))
/*
=> java.lang.UnsupportedOperationException: empty.tail
  at scala.collection.TraversableLike$class.tail(TraversableLike.scala:449)
  at scala.collection.AbstractTraversable.tail(Traversable.scala:104)
  at .print_counts_rec(<console>:13)
 */

// 5 //

// skip

// 6 //

import java.util.Calendar._
val stringsToCalConst = collection.mutable.LinkedHashMap(
  "Monday" -> MONDAY,
  "Tuesday" -> TUESDAY,
  "Wednesday" -> WEDNESDAY,
  "Thursday" -> THURSDAY,
  "Friday" -> FRIDAY,
  "Saturday" -> SATURDAY,
  "Sunday" -> SUNDAY)
println(stringsToCalConst)

// 7 //

/*
Print a table of all Java properties, like this:
          java.runtime.name             | Java(TM) SE Runtime Environment
          sun.boot.library.path         | /home/apps/jdk1.6.0_21/jre/lib/i386
You need to find the length of the longest key before you can print the table.
 */

import scala.collection.JavaConversions.propertiesAsScalaMap

def print_props(Unit) : Unit ={
  val props: scala.collection.Map[String, String] = System.getProperties()
  val max_len = props.maxBy(_._1.size)._1.size
  props.map{ case(k,v) =>
    println(k + " "*(max_len - k.size) + "| " + v)
  }
}

// 8 // 

/* Write a function minmax(values: Array[Int]) that returns a pair containing the smallest and largest value in the array. */

def minmax(values: Array[Int]) : Tuple2[Int, Int] = {
  values.foldLeft((values.head,0))((b,a) =>
    if (a < b._1) (a, b._2)
    else if (a > b._2) (b._1, a)
    else b)
}

// 9 //

//Write a function lteqgt(values: Array[Int], v: int) that returns a triple containing count of values less than v, equal to v, and greater than v.

def lteqgt(vals: Array[Int], v: Int) : Tuple3[Int,Int,Int] = {
  vals.foldLeft((0,0,0))((b,a) =>
    if (a > v) (b._1, b._2, b._3 + 1)
    else if (a == v) (b._1, b._2 + 1, b._3)
    else (b._1 + 1, b._2, b._3)
  )
}

// 10 //

// Q: What happens when you zip together two strings, such as "Hello".zip("World")? Come up with a plausible use case.

// A: you get a vector of tuples like:  Vector((H,W), (e,o), (l,r), (l,l), (o,d))
// you could use this to find palindromes! 


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

class BankAccount(private var bal: Double){
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

class Time(private val hrs: Int, private val min: Int ){
  def before(other: Time) =
    hrs < other.hrs || hrs == other.hrs && min < other.min
}

// 4 //

/* Reimplement the Time class from the preceding exercise so that the internal representation is the number of minutes since midnight (between 0 and 24×60-1). Do not change the public interface. That is, client code should be unaffected by your change.*/


class Time(val hrs: Int, val min: Int) {
  private val minSinceMid: Int = 60*hrs + min
  def before(other: Time) = minSinceMid < other.minSinceMid
}

// 5 //

//Make a class Student with read-write JavaBeans properties name (of type String) and id (of type Long). What methods are generated? (Use javap to check.) Can you call the JavaBeans getters and setters in Scala? Should you?

class Student(@BeanProperty var name: String, @BeanProperty var id: Int)

// 6 //

// In the Person class of Section 1, provide a primary constructor that turns negative ages to 0.

class Person(var age: Int) {
  age = if (age > 0) age else 0
}

// 7 //

// Write a class Person with a primary constructor that accepts a string containing a first name, a space, and a last name, such as new Person("Fred Smith"). Supply read-only properties firstName and lastName. Should the primary constructor parameter be a var, a val, or a plain parameter? Why?

class Person(fullName: String){
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
  val defSalary: String = defSalary
}

// more succinctly:

class Employee(val name: String = "John Q. Public", val salary: Double = 0.0)

// ** CH 6 ** //

