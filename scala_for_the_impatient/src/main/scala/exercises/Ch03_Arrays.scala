package exercises

import scala.util.Random
import scala.collection.mutable.ArrayBuffer

/**
 * Created by aguestuser on 12/31/14.
 */
class Ch03_Arrays {
  //1//

  // imperative
  def make_array(n: Int) : Array[Int] = {
    for (item <- new Array[Int](n)) yield Random.nextInt(n)
  }

  // functional
  def make_array_1(n: Int) : Array[Int] = {
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

  def swap_1(arr: Array[Int]) : Array[Int] = {
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
  def avg_1(a: Array[Double]) : Double = {
    a.foldLeft(0.0)(_ + _) / a.length
  }

  // 6 //
  def rev_sort_2(a: Array[Int]) : Array[Int] = {
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

  def keepOnlyTheFirstNegative(ints: List[Int]) : List[Int] = {
    ints.span(_ >= 0) match {
      case (positives, List()) => positives
      case (positives, head :: tail) =>
        positives ++ (head :: tail.filter(_ >= 0))
    }
  }

}
