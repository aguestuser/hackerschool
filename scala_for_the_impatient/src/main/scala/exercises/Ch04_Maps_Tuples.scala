package exercises

/**
 * Created by aguestuser on 12/31/14.
 */
class Ch04_Maps_Tuples {
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

  // import scala.collection.immutable.ListMap

  // def count_sort_counts(path: String) : ListMap[String,Int]  = {
  //   scala.collection.immutable.ListMap(
  //     scala.io.Source.fromFile(path)
  //     .mkString
  //     .split("""(\.?\s+|\.)""")
  //     .foldLeft(Map[String, Int]())((b,a)
  //       => b + (a -> (b.getOrElse(a, 0) + 1)))
  //     .toSeq.sortWith(_._2 > _._2):_*
  //   )
  // }

  def count_sort_counts(path: String) : List[(String,Int)]  = {
    scala.io.Source.fromFile(path)
      .mkString
      .split("""(\.?\s+|\.)""")
      .toList
      .groupBy(w => w)
      .mapValues(_.size)
      .toList
      .sortWith(_._2 > _._2)
  }

  def print_counts_rec(map: List[(String,Int)]): Unit = map match {
    case List() => ()
    case (k, v) :: rest =>
      println(k + ": " + v)
      print_counts_rec(rest)
  }

  print_counts_rec(count_sort_counts("../sample_data/lorem_ipsum.txt"))

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

  def print_props() : Unit ={
    val props: scala.collection.Map[String, String] = System.getProperties()
    val max_len = props.maxBy(_._1.size)._1.size
    props.map{ case(k,v) =>
      println(k + " "*(max_len - k.size) + "| " + v)
    }
  }

  // 8 //

  /* Write a function minmax(values: Array[Int]) that returns a pair containing the smallest and largest value in the array. */

  def minmax(values: Array[Int]) : (Int, Int) = {
    values.foldLeft((values.head,0))((b,a) =>
      if (a < b._1) (a, b._2)
      else if (a > b._2) (b._1, a)
      else b)
  }

  // 9 //

  //Write a function lteqgt(values: Array[Int], v: int) that returns a triple containing count of values less than v, equal to v, and greater than v.

  def lteqgt(vals: Array[Int], v: Int) : (Int, Int, Int) = {
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
}
