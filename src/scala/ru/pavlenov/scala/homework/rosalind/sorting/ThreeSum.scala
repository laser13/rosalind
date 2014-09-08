package ru.pavlenov.scala.homework.rosalind.sorting

import ru.pavlenov.scala.utils.File
import scala.collection.parallel._

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
    val t2 = "from http://rosalind.info/problems/3sum/"
    println(t1 + "\n" + t2)
    println("=" * t2.length)

    val data = File.fromData(this)

    val n = data.head.split(" ")(1).toInt
    val matrix = data.tail
    var res = ""

    def find(a: Array[Int]): String = {
      for (i <- 0 until n; j <- i+1 until n; l <- j+1 until n) {
        if (a(i) + a(j) + a(l) == 0) {
          return (i + 1) + " " + (j + 1) + " " + (l + 1)
        }
      }
      "-1"
    }

    matrix.par.foreach(row => {
      val a = row.split(" ").map(_ toInt)
      val s = find(a)
      println("*")
      res += s + "\n"
    })

    File.toFile(this, "answer", res)
    println("187374.84")
    println("90029.63")

  }



}