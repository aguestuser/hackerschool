package exercises

/**
 * Created by aguestuser on 1/1/15.
 */
class Chapter_7_Packages {
// 1.
// Write an example program to demonstrate that package com.horstmann.impatient is not the same as package com package horstmann package impatient

  // nope.

//  2.
// Write a puzzler that baffles your Scala friends, using a package com that isn't at the top level.

  //nope

//  3.
// Write a package random with functions nextInt(): Int, nextDouble(): Double, and setSeed(seed: Int): Unit. To generate random numbers, use the linear congruential generator next = previous × a + b mod 2n, where a = 1664525, b = 1013904223, and n = 32.

  // see exercises.random package object

//  4.
// Why do you think the Scala language designers provided the package object syntax instead of simply letting you add functions and variables to a package?

  /*  no idea! (interoperability with Java?)
      see here for better answer: http://qnalist.com/questions/5443738/package-object
      has to do with constraint that there can only be one package object per package but packages (unlike package objects can be described across multiple files)*/

//  5.
// What is the meaning of private[com] def giveRaise(rate: Double)? Is it useful?

  // please ask me questions whose answer comes in the form of code, thank you!!!

  /* best guess: anywhere inside the `people` package, you can call .getRaise on any instance of the Person class, passing a rate to which you want to multiply person.payRate by. sounds useful to me! seems like exposing #getRaise to only other objects in the `people` package is probably a good idea, since it would allow `Managers` or `Owners` for example to both give raises to an `Employee` without having to be members of the same class but keep `Customer` from doing so. */

//  6.
// Write a program that copies all elements from a Java hash map into a Scala hash map. Use imports to rename both classes.

  // see object convertHash below

//  7.
// In the preceding exercise, move all imports into the innermost scope possible.

  // ditto

// 8.
/* What is the effect of
    import java._
    import javax._
   Is this a good idea?
*/

  // don't know! don't particularly care! :)

// 9.
// Write a program that imports the java.lang.System class, reads the user name from the user.name system property, reads a password from the Console object, and prints a message to the standard error stream if the password is not "secret". Otherwise print a greeting to the standard output stream. Do not use any other imports, and do not use any qualified names (with dots).

  // see `getPassword` object below

// 10.
// Apart from StringBuilder, which other members of java.lang does the scala package override?

  // again: please with the code questions...

}

object convertHash extends App {
  import java.util.{HashMap => Jash}

import scala.collection.JavaConverters._
  import scala.collection.mutable.{Map => Sash}

  def jashToSash(jash: Jash[String,_]) : Sash[String,_] = jash.asScala

  val input = new Jash[String, Any]
  input.put("name", "Austin")
  input.put("age", 34)
  input.put("residence", "Brooklyn")

  println(jashToSash(input))
}

object getPassword extends App {
  import java.lang.System.{ console => c, getProperty => p }

  val user = p("user.name")
  val pw = c.readPassword("Please input password\n").mkString("")
  if (pw == "secret") Console.println("Welcome " + user + "!")
  else Console.err.println("Sorry. You provided the wrong password.")
}