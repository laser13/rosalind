package ru.pavlenov.scala.homework.rosalind.combinatorics

import ru.pavlenov.scala.utils.Comb

/**
 * ⓭ + 14
 * Какой сам? by Pavlenov Semen 13.07.14.
 * Counting Subsets 
 * http://rosalind.info/problems/sset/
 *
 * Given:
 * A positive integer n (n≤1000).
 *
 * Return:
 * The total number of subsets of {1,2,…,n} modulo 1,000,000.
 */

object Sset {

  def start() {

    println("Counting Subsets ")
    println("from http://rosalind.info/problems/sset/")
    println("==========================")

    val n = 872
    println(Comb.countSubset(n))
    println(Comb.countSubset(n) % 1000000)

  }

}