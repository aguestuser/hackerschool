package exercises

/**
 * Created by aguestuser on 1/2/15.
 */
class Ch12_HigherOrderFunctions {
  // 1.
  /*
  * Write a function `values(fun: (Int) ⇒ Int, low: Int, high: Int)
  * that yields a collection of function inputs and outputs in a given range
  * For example, `values(x => x * x, -5, 5)` should produce a collection
  * of pairs `-5, 25), (-4, 16), (-3, 9), .... (5, 25)
  * */

  def values(fn: (Int) ⇒ Int, low: Int, high: Int): List[(Int, Int)] =
    (low to high).map { x => (x, fn(x))}.toList

  // 2.
  /*
  * How do you get the largest element of an array with reduceLeft?
  * */

  def max(ints: List[Int]) =
    ints.reduceLeft { (a, b) => if (b > a) b else a}

  // 3.
  /*
  * Implement the factorial function
  * using `to` and `reduceLeft` without a loop or recursion
  * */

  def factorial(num: Int): Int =
    if (num < 0) 1
    else 2 to num reduceLeft {
      _ * _
    }

  def altFactorial(num: Int): Int =
    if (num < 0) 1 else (2 to num).product

  // 4.
  /*
  * The previous implementation needed a special case when n < 1
  * Show how you can avoid this with foldLeft
  * */

  def foldFactorial(num: Int): Int =
    (1 to num).foldLeft(1) {
      _ * _
    }

  // 5.
  /*
  * Write a function `largest(fun: (Int) ⇒ Int, inputs: Seq[Int])
  * that yields the largest value of a function within a given sequence of inputs.
  * For example, `largest(x => 10 * x - x * x, 1 to 10)
  * */

  def largest(fun: (Int) => Int, inputs: Seq[Int]): Int = inputs.map(fun).max

  // 6.
  /*
  * Modify the previous function to return the input at which the output is largest
  * For example: `largestAt(x => 10 * x - x * x, 1 to 10)` should return 5
  * */


  def largestAt(fun: (Int) => Int, inputs: Seq[Int]): Int =
    inputs.reduceLeft { (a, b) => if (fun(b) > fun(a)) b else a}

  def altLargestAt(fun: (Int) => Int, inputs: Seq[Int]): Int =
    inputs.map(fun).zip(inputs).max._2

  // 7.

  /*
  * It's easy to get a sequence of pairs, for example:
  * `val pairs = (1 to 10) zip (11 to 20)`
  * Now suppose you want to do something with such a sequence
  * -- say, add up the values. But you can't do `pairs.map(_ + _)`
  * The function `_ + _` takes two Int parameters, not an (Int, Int) pair.
  * Write a function `adjustToPair` that receives a function of type
  * `(Int, Int) => Int` and returns the equivalent function
  * that operates on a pair. For example, `adjustToPair(_ * _)((6,7)) is 42.
  * Then use this function in conjunction with `map` to compute the sums
  * of the elements in `pairs`.
  * */

  def adjustToPairs(fun: (Int, Int) => Int)(pair: (Int, Int)) =
    fun(pair._1, pair._2)

  // (1 to 10).zip(11 to 20).map(adjustToPairs(_ + _))
  // => Vector(12, 14, 16, 18, 20, 22, 24, 26, 28, 30)

  // 8.

  /*
  * In Section 12.8 "Curring" on page 149, you saw the `corresponds`
  * method used with two arrays of strings. Make a call to corresponds
  * that checks whether the elements in an array of strings have
  * the lengths given in an array of integers
  * */

  def haveLengths(strs: Array[String], lens: Array[Int]): Boolean =
    strs.corresponds(lens)(_.size == _)

  // 9.

  /*
  * Implement corresponds withtout currying. Then try to the call
  * from the preceding exercise. What problem do you encounter?
  * */

  def altCorresponds[A, B](seq1: Seq[A], seq2: Seq[B], func: (A, B) => Boolean): Boolean =
    seq1.zip(seq2).forall(pair => func(pair._1, pair._2))

  def altHaveLengths(strs: Array[String], lens: Array[Int]): Boolean =
    altCorresponds(strs, lens, (x: String, y: Int) => x.size == y)

  /*
  * problem: type inference breaks down b/c
  * type of lens isn't already known when predicate is evaluated.
  * thus, it's necessary to get hella verbose
  * specifying input/output types in predicate
  * */

  def unless(cond: => Boolean)(block: => Unit){ if (!cond) block }

  // try to implement els

  abstract class DoSomething { def els(block: => Unit) }

  def altUnless(condition: => Boolean)(block: => Unit): Unit = {
    if (!condition)
      new DoSomething {
        block;
        def els(block: => Unit){}
      }
    else
      new DoSomething {
        def els(block: => Unit) {
          block
        }
      }
  }
 }
