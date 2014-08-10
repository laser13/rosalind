package ru.pavlenov.scala.homework.rosalind.combinatorics

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 39
 * Какой сам? by Pavlenov Semen 06.08.14.
 * Introduction to Alternative Splicing 
 * http://rosalind.info/problems/Aspc/
 *
 * Given:
 * Positive integers n and m with 0≤m≤n≤2000.
 *
 * Return:
 * The sum of combinations C(n,k) for all k satisfying m≤k≤n, modulo 1,000,000. In shorthand, ∑nk=m(nk).
 */

object Aspc {

  def start() {

    println("Introduction to Alternative Splicing ")
    println("from http://rosalind.info/problems/Aspc/")
    println("==========================")

    val data = File.fromData(this)

  }

}