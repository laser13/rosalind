package ru.pavlenov.scala.homework.rosalind.div_and_con

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 17
 * Какой сам? by Pavlenov Semen 06.08.14.
 * Binary Search 
 * http://rosalind.info/problems/Bins/
 *
 * Given:
 * Two positive integers n≤105 and m≤105, a sorted array A[1..n] of integers from −105 to 105 and a list of m integers −105≤k1,k2,…,km≤105.
 *
 * Return:
 * For each ki, output an index 1≤j≤n s.t. A[j]=ki or "-1" if there is no such index.
 */

object Bins {

  def start() {

    println("Binary Search ")
    println("from http://rosalind.info/problems/Bins/")
    println("==========================")

    val data = File.fromData(this)

  }

}