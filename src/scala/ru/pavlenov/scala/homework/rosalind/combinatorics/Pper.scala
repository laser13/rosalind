package ru.pavlenov.scala.homework.rosalind.combinatorics

import ru.pavlenov.scala.utils.{Comb, Prob}

/**
 * ⓭ + 52
 * Какой сам? by Pavlenov Semen 05.07.14.
 * Partial Permutations
 * http://rosalind.info/problems/pper/
 *
 * Given:
 * Positive integers n and k such that 100≥n>0 and 10≥k>0.
 *
 * Return:
 * The total number of partial permutations P(n,k), modulo 1,000,000.
 */

object Pper {

  def start() {

    println("Start Pper")
    println("Start: Partial Permutations = [http://rosalind.info/problems/pper/]")
    println("==========================")

    val n = 84
    val k = 10
    val m = 1000000

    println(Comb.assign(n, k).mod(m))

  }

}