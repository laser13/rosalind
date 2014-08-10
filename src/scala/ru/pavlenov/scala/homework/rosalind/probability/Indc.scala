package ru.pavlenov.scala.homework.rosalind.probability

import ru.pavlenov.scala.utils.{Comb, Prob, File}

/**
 * ⓭ + 48
 * Какой сам? by Pavlenov Semen 06.08.14.
 * Independent Segregation of Chromosomes
 * http://rosalind.info/problems/Indc/
 *
 * Given:
 * A positive integer n≤50.
 *
 * Return:
 * An array A of length 2n in which A[k] represents the common logarithm of the probability that two diploid siblings share at least k of their 2n chromosomes (we do not consider recombination for now).
 */

object Indc {

  /**
   * R
   * > n = 5
   * > cat( log10( pbinom( (n*2-1):0, n*2, 0.5 ) ) )
   */

  def start() {

    println("Independent Segregation of Chromosomes")
    println("from http://rosalind.info/problems/Indc/")
    println("==========================")

    // Число пар хромосом
    val n = 48
    // Общее кол-во вариантов перекомбинации хромосом 2^n гамет с одной стороны
    // и 2^n гамет с другой итого 2^2*n вариантов потомства
    var variant = BigInt(2).pow(2 * n)
    // Вероятность одного варианта 1/2^2*n, берём логарифм чтобы не терять точность
    val prob = -2*n*math.log10(2)
    val A = new Array[Double](2 * n)

    println(A.length)
    println(-2*n*math.log10(2))

    for (i <- 0 until 2*n) {
      variant -= Comb.choose(2*n, i)
      A(i) = prob + math.log10(variant.toDouble)
    }

    for (a <- A) {
      print("%.03f ".format(a).replace(",", ".").replace("-0.000", "0.000"))
    }
    println()

  }

}