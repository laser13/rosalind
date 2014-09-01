package ru.pavlenov.scala.homework.rosalind.sorting

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 15
 * Какой сам? by Pavlenov Semen 01.09.14.
 * Insertion Sort
 * http://rosalind.info/problems/Ins/
 *
 * Given: A positive integer n≤103 and an array A[1..n] of integers.
 *
 * Return: The number of swaps performed by insertion sort algorithm on A[1..n].
 */

object Ins {

  def start() {

    val t1 = "Insertion Sort"
    val t2 = "from http://rosalind.info/problems/Ins/"
    println(t1 + "\n" + t2)
    println("=" * t2.length)

    val data = File.fromData(this)
    val n = data.head
    val arr = data(1).split(" ").map(_ toInt)

    println(arr.mkString(" "))
    insertionSort(arr)
    println(arr.mkString(" "))

  }

  def insertionSort(a: Array[Int]): Unit = {
    var cnt = 0
    for (i <- 1 until a.length) {
      var k = i
      while (k > 0 && a(k) < a(k-1)) {
        swap(a, k-1, k)
        cnt += 1
        k -= 1
      }
    }

    println(cnt)
  }

  def swap(a: Array[Int], i: Int, j: Int): Unit = {
    val v = a(i)
    a(i) = a(j)
    a(j) = v
  }

}