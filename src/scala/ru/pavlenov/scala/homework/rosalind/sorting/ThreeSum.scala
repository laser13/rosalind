package ru.pavlenov.scala.homework.rosalind.sorting

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 09
 * Какой сам? by Pavlenov Semen 02.09.14.
 * 3SUM
 * http://rosalind.info/problems/ThreeSum/
 *
 * Given: A positive integer k≤20, a postive integer n≤104, and k arrays of size n containing integers from −105 to 105.
 *
 * Return: For each array A[1..n], output three different indices 1≤p<q<r≤n such that A[p]+A[q]+A[r]=0 if exist, and "-1" otherwise.
 */

object ThreeSum {

  def start() {

    val t1 = "3SUM"
    val t2 = "from http://rosalind.info/problems/ThreeSum/"
    println(t1 + "\n" + t2)
    println("=" * t2.length)

    val data = File.fromData(this)


  }

}