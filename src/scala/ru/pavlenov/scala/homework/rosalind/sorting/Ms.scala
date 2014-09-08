package ru.pavlenov.scala.homework.rosalind.sorting

import ru.pavlenov.scala.utils.{Sort, File}

/**
 * ⓭ + 08
 * Какой сам? by Pavlenov Semen 02.09.14.
 * Merge Sort
 * http://rosalind.info/problems/Ms/
 *
 * Given: A positive integer n≤105 and an array A[1..n] of integers from −105 to 105.
 *
 * Return: A sorted array A[1..n].
 */

object Ms {

  implicit def IntIntLessThan(x: Int, y: Int) = x < y

  def start() {

    val t1 = "Merge Sort"
    val t2 = "from http://rosalind.info/problems/ms/"
    println(t1 + "\n" + t2)
    println("=" * t2.length)

    val data = File.fromData(this)

    val n = data(0).toInt
    val a = data(1).split(" ").map(_ toInt).toList

    val sort = Sort.mergeSort(a)

    File.toFile(this, ".answer", sort.mkString(" "))


  }

}