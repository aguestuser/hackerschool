package exercises

import scala.io.Source


/**
 * Created by aguestuser on 1/3/15.
 */
class Ch09_FilesAndRegexes {
//  1.
// Write a Scala code snippet that reverses the lines in a file (making the last line the first one, and so on).

  def revLines(path: String) : List[String] =
    scala.io.Source.fromFile(path).getLines().toList.reverse

//  2. Write a Scala program that reads a file with tabs, replaces each tab with spaces so that there are tab stops every n columns, and writes the result to the same file.

  /* hmm...
  * https://github.com/calinburloiu/Scala-for-the-Impatient--Exercises/blob/master/src/exercises/c09_files_regex/TabReplacer.scala
  * https://github.com/Gerhut/scala-for-the-impatient/blob/master/Chapter9/2.scala
  * */



//  3. Write a Scala code snippet that reads a file and prints all words with more than 12 characters in the file to the console. Extra credit if you can do this in a single line.

  def printLongWords(path: String): Unit =
    print(Source.fromFile(path).mkString.split("""\s""").filter(_.length > 12).mkString("\n"))


//  4.
// See bottom of file

//  5. Write a Scala program that writes the powers of 2 and their reciprocals to a file, with the exponent ranging from 0 to 20. Line up the columns:
//  11
//  2 0.5 4 0.25
//  ... ...
//  6. Make a regular expression for quoted strings "like this, maybe with \" or \\" in a Java or C++ program. Write a Scala program that prints out all such strings in a source file.
//  7. Write a Scala program that reads a text file and prints all tokens in the file that are not floating-point numbers. Use a regular expression.
//  8. Write a Scala program that prints the src attributes of all img tags of a web page. Use regular expressions and groups.
//  9. Write a Scala program that counts how many files with extension .class are in a given directory and its subdirectories.
//  10. Expand the example with the serializable Person class that stores a collection of friends. Construct a few Person objects, make some of them friends of another, and then save an Array[Person] to a file. Read the array back in and verify that the friend relations are intact.
}

// 4.
// Write a Scala program that reads a text file containing only floating-point numbers. Print the sum, average, maximum, and minimum of the numbers in the file.

class NumAnalyzer extends App {
  val path : String = args(0)
  val doubles : List[Double] =
    Source.fromFile(path).mkString.split( """\s""").map(_.toDouble).toList
  val labels : List[String] =
    List("SUM:", "AVERAGE:", "MAX:", "MIN:")
  val fns : List[(List[Double] => Double)] =
    List( {_.sum}, { x => x.sum / x.length}, { _.max}, { _.min} )

  println("SOME FACTS ABOUT THESE NUMBERS:")
  labels.zip(fns).map{case (label, fn) => println(label + ":" +fn(doubles)) }
}