package ru.pavlenov.scala.homework.rosalind.div_and_con

import java.util

import ru.pavlenov.scala.utils.File

import scala.util.Sorting

/**
 * ⓭ + 17
 * Какой сам? by Pavlenov Semen 06.08.14.
 * Binary Search 
 * http://rosalind.info/problems/Bins/
 *
 * Given:
 * Two positive integers n≤105 and m≤10^5^, a sorted array A[1..n] of integers from −10^5^ to 10^5^ and a list of m integers −10^5^≤k1,k2,…,km≤10^5^.
 *
 * Return:
 * For each ki, output an index 1≤j≤n s.t. A[j]=ki or "-1" if there is no such index.
 */

object Bins {

  def start() {

    println("Binary Search ")
    println("from http://rosalind.info/problems/Bins/")
    println("==========================")

    var data = File.fromData(this)

    val n = data.head; data = data.tail
    val m = data.head; data = data.tail

    val array = data.head.split(" ").map(_ toInt); data = data.tail
    val values = data.head.split(" ").map(_ toInt)

    for (value <- values) {
      print(util.Arrays.binarySearch(array, value) + " ")
    }
    println()

  }

}