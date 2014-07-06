package ru.pavlenov.scala.utils

/**
 * ⓭ + 44
 * Какой сам? by Pavlenov Semen 05.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */
object Comb {

  /**
   * Считаем число Каталана
   * С[n+1] = (2*(2*n+1)/(n+2))*C[n]
   *
   * @param n
   * @return
   */
  def catalan(n: Int): BigInt = {
    var c: BigInt = 1
    for (i <- 1 to n - 1) c = (c * (4 * i + 2)) / (i + 2)
    c
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
  def choose(n: Int, k: Int): Long = {
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
  def assign(n: Int, k: Int): BigInt = {
    fact(n) / fact(n - k)
  }

  /**
   * Считаем факториал числа
   *
   * @param n
   * @return
   */
  def fact(n: Int): BigInt = {
    (1 to n).map(BigInt.int2bigInt).product
  }

}
