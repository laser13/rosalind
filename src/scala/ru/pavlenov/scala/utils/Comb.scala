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
    for (i <- 1 to n - 1) {
      c = (c * (4 * i + 2)) / (i + 2)
    }
    c
  }

}
