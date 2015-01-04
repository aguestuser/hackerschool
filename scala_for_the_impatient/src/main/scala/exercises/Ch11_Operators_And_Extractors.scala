package exercises

/**
 * Created by aguestuser on 1/3/15.
 */
class Ch11_Operators_And_Extractors {
//  1. According to the precedence rules, how are 3 + 4 -> 5 and 3 -> 4 + 5 evaluated?
//  2. The BigInt class has a pow method, not an operator. Why didn’t the Scala library designers choose ** (as in Fortran) or ^ (as in Pascal) for a power operator?
//  3. Implement the Fraction class with operations + - * /. Normalize fractions, for example turning 15/–6 into –5/2. Divide by the greatest common divisor, like this:
//  class Fraction(n: Int, d: Int) {
//    private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d);
//    private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d);
//    override def toString = num + "/" + den
//    def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0
//    def gcd(a: Int, b: Int): Int = if (b == 0) abs(a) else gcd(b, a % b)
//    ...
//  }
// 4. Implement a class Money with fields for dollars and cents. Supply +, - operators as well as comparison operators == and <. For example, Money(1, 75) + Money(0, 50) == Money(2, 25) should be true. Should you also supply * and / operators? Why or why not?
// 5. Provide operators that construct an HTML table. For example,
//    Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
//  should produce
//  <table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gosling...
// 6. Provide a class ASCIIArt whose objects contain figures such as
//    /\_/\
//   ( ' ' )
//   (  -  )
//    | | |
//   (__|__)

//    Supply operators for combining two ASCIIArt figures horizontally
//    /\_/\    -----
//   ( ' ' )  / Hello \
//   (  -  ) <  Scala |
//    | | |   \ Coder /
//   (__|__)    -----
//      or vertically. Choose operators with appropriate precedence.
// 7. Implement a class BitSequence that stores a sequence of 64 bits packed in a Long value. Supply apply and update operators to get and set an individual bit.
// 8. Provide a class Matrix—you can choose whether you want to implement 2 × 2 matrices, square matrices of any size, or m × n matrices. Supply operations + and *. The latter should also work with scalars, for example mat * 2. A single element should be accessible as mat(row, col).
// 9. Define an unapply operation for the RichFile class that extracts the file path, name, and extension. For example, the file /home/cay/readme.txt has path /home/cay, name readme, and extension txt.
// 10. Define an unapplySeq operation for the RichFile class that extracts all path segments. For example, for the file /home/cay/readme.txt, you should produce a sequence of three segments: home, cay, and readme.txt.
}
