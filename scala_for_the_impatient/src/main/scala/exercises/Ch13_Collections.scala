package exercises

/**
 * Created by aguestuser on 1/3/15.
 */
class Ch13_Collections {
  // THIS CHAPTER WAS AMAZING! BE SURE TO COME BACK AND DO THESE!
//  1. Write a function that, given a string, produces a map of the indexes of all characters. For example, indexes("Mississippi") should return a map associating 'M' with the set {0}, 'i' with the set {1, 4, 7, 10}, and so on. Use a mutable map of characters to mutable sets. How can you ensure that the set is sorted?
//    2. Repeat the preceding exercise, using an immutable map of characters to lists.
//  3. Write a function that removes all zeroes from a linked list of integers.
//  4. Write a function that receives a collection of strings and a map from strings to integers. Return a collection of integers that are values of the map corresponding to one of the strings in the collection. For example, given Array("Tom", "Fred", "Harry") and Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5), return Array(3, 5). Hint: Use flatMap to combine the Option values returned by get.
//  5. Implement a function that works just like mkString, using reduceLeft.
//  6. Given a list of integers lst, what is (lst :\ List[Int]())(_ :: _)? (List[Int]() /: lst)(_ :+ _)? How
//  can you modify one of them to reverse the list?
//  7. In Section 13.11, “Zipping,” on page 171, the expression (prices zip quantities) map { p => p._1 * p._2 } is a bit inelegant. We can’t do (prices zip quantities) map { _ * _ } because _ * _ is a function with two arguments, and we need a function with one argument that is a tuple. The tupled method of the Function2 object changes a function with two arguments to one that takes a tuple. Apply tupled to the multiplication function so you can map it over the list of pairs.
//  8. Write a function that turns an array of Double values into a two-dimensional array. Pass the number of columns as a parameter. For example, with Array(1, 2, 3, 4, 5, 6) and three columns, return Array(Array(1, 2, 3), Array(4, 5, 6)). Use the grouped method.
//  9. Harry Hacker writes a program that accepts a sequence of file names on the command line. For each, he starts a new thread that reads the file and updates a letter frequency map, declared as
//  val frequencies = new scala.collection.mutable.HashMap[Char, Int] with
//    scala.collection.mutable.SynchronizedMap[Char, Int]
//  When reading a letter c, he calls
//    
//    frequencies(c) = frequencies.getOrElse(c, 0) + 1
//  Why won’t this work? Will it work if he used instead
//  
//  import scala.collection.JavaConversions.asScalaConcurrentMap
//  val frequencies: scala.collection.mutable.ConcurrentMap[Char, Int] =
//    new java.util.concurrent.ConcurrentHashMap[Char, Int]
//  10. Harry Hacker reads a file into a string and wants to use a parallel collection to update the letter frequencies concurrently on portions of the string. He uses the following code:
//    ￼￼￼￼￼￼￼￼￼￼
//  ￼
//  val frequencies = new scala.collection.mutable.HashMap[Char, Int]
//  for (c <- str.par) frequencies(c) = frequencies.getOrElse(c, 0) + 1
//  Why is this a terrible idea? How can he really parallelize the computation? (Hint: Use aggregate.)
  
}
