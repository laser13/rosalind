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
    for (i <- 1 until n) c = (c * (4 * i + 2)) / (i + 2)
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
    val j = if (k > n - k) n - k else k
    var result: Long = 1
    for (i <- 1 to j) {
      result *= (n - (j - i))
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
  def assign(n: Int, k: Int): BigInt = fact(n) / fact(n - k)

  /**
   * Считает кол-во всех возможных подмножеств из множества размером n
   * пример: {1, 2, 3} => 2^3=8, {∅, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}
   * https://math.stackexchange.com/questions/236659/number-of-subsets-of-a-set-having-r-elements/236671#236671?newreg=053c78da5ac04ea5bb19a6cb5682ae19
   *
   * @param n
   * @return
   */
  def countSubset(n: Int): BigInt = BigInt(2).pow(n)

  /**
   * Считаем факториал числа
   *
   * @param n
   * @return
   */
  def fact(n: Int): BigInt = (1 to n).map(BigInt.int2bigInt).product

}
