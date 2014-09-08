package ru.pavlenov.scala.homework.rosalind.sorting

import ru.pavlenov.scala.utils.{Sort, File}

/**
 * ⓭ + 07
 * Какой сам? by Pavlenov Semen 02.09.14.
 * Merge Two Sorted Arrays
 * http://rosalind.info/problems/Mer/
 *
 * Given: A positive integer n≤105 and a sorted array A[1..n] of integers from −105 to 105, a positive integer m≤105 and a sorted array B[1..m] of integers from −105 to 105.
 *
 * Return:  A sorted array C[1..n+m] containing all the elements of A and B.
 */

object Mer {

  implicit def IntIntLessThan(x: Int, y: Int) = x < y

  def start() {

    val t1 = "Merge Two Sorted Arrays"
    val t2 = "from http://rosalind.info/problems/Mer/"
    println(t1 + "\n" + t2)
    println("=" * t2.length)

    val data = File.fromData(this)

    val n = data(0).toInt
    val m = data(2).toInt

    val a1 = data(1).split(" ").map(_ toInt).toList
    val a2 = data(3).split(" ").map(_ toInt).toList

    val res = Sort.merge(a1, a2)

    println(res.mkString(" "))

  }

}