package ru.pavlenov.scala.utils

import org.jscience.mathematics.number.LargeInteger

/**
 * ⓭ + 38
 * Какой сам? by Pavlenov Semen 04.07.14.
 */
object Prob {

  def gcContent(text: String, gc: Double) : Double = {

    val probAT = math.log((1 - gc) / 2)
    val probGC = math.log(gc / 2)

    val countAT = text.count(_ == 'A') + text.count(_ == 'T')
    val countGC = text.count(_ == 'G') + text.count(_ == 'C')

    probAT * countAT + probGC * countGC

  }

  def gcContent(counts: Map[Char, Int], gc: Double) : Double = {

    val probAT = math.log((1 - gc) / 2)
    val probGC = math.log(gc / 2)

    val countAT = counts('A') + counts('T')
    val countGC = counts('G') + counts('C')

    probAT * countAT + probGC * countGC

  }

  /**
   * Число сочетаний без повторений (n различных элементов, взятых по m)
   * http://www.webmath.ru/web/prog21_1.php
   *
   * Сочетаниями из n элементов по m элементов называются комбинации, составленные из данных n элементов по m элементов,
   * которые различаются хотя бы одним элементом
   * (отличие сочетаний от размещений в том, что в сочетаниях не учитывается порядок элементов).
   *
   * @param n
   * @param k
   * @return
   */
  def choose(n: Int, k: Int) : Long = {
    // Use symmetry of Pascal's triangle
    val k1 = if (k > n - k) n - k else k
    var result: Long = 1
    for (i <- 1 to k1) {
      result *= (n - (k1 - i))
      result /= i
    }
    result
  }

  /**
   * Число размещений без повторений из n по m
   * http://www.webmath.ru/web/prog27_1.php
   *
   * A(k,n) = n! / (n - m)!
   *
   * Размещениями из n элементов по m элементов (m < n) называются комбинации,
   * составленные из данных n элементов по m элементов, которые отличаются
   * либо самими элементами, либо порядком элементов.
   *
   * @param n
   * @param k
   * @return
   */
  def assign(n: Int, k: Int) : BigInt = {
    val r: BigInt = fact(n)./(fact(n - k))
    r
  }

  def fact(n: Int) : BigInt = {
    (1 to n).map(BigInt.int2bigInt).product
  }

}
