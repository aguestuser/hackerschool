package exercises

/**
 * Created by aguestuser on 12/31/14.
 */
class Ch02_ControlFlowFunctions {
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

  def signum_alt(num: Int): Int =
    if (num < 0)
      -1 else if (num > 0)
      1 else
      0

  def signum_alt1(num: Int) = {
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

  def countdown_alt(n: Int) : Int = {
    if (n == 0){
      println(0)
      n
    }
    else {
      println(n)
      countdown_alt(n-1)
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

  def uni_prod_1(s: String) : Int = {
    if (s == "") 1
    else s.charAt(0) * uni_prod_1(s.tail)
  }

  //alt syntax:

  def uni_prod_2(s: String) : Int =
    if (s == "")
      1 else s.charAt(0) * uni_prod_2(s.tail)

  // 7 //

  def uni_prod_3(s: String) : Int =
    s.foldLeft(1)((a,b) => a*b)

  // 8 & 9 //

  // see above!

  // 10 //

  def to_nth(x:Int, n: Int) : Int = {
    if (n == 0) 1
    else if (n < 0)  1 / to_nth(x, -n)
    else if (n > 0 & n % 2 == 0) to_nth(x, n/2) * to_nth(x, n/2)
    else x * to_nth(x, n-1)
  }
}
