package ru.pavlenov.scala.homework.rosalind.sorting

import java.util.concurrent.atomic.AtomicInteger

import ru.pavlenov.scala.utils.{Base, File}

import scala.collection.JavaConverters._
import scala.collection.immutable.{TreeMap, HashMap}
import scala.collection.JavaConversions._
import scala.collection.mutable

/**
 * ⓭ + 37
 * Какой сам? by Pavlenov Semen 06.09.14.
 * 3SUM
 * http://rosalind.info/problems/IIISUM/
 *
 * Given:
 * A positive integer k≤20, a postive integer n≤104, and k arrays of size n containing integers from −105 to 105.
 *
 * Return:
 * For each array A[1..n], output three different indices 1≤p<q<r≤n such that A[p]+A[q]+A[r]=0 if exist, and "-1" otherwise.
 */

object IIISUM {

  def start() {

    println("3SUM")
    println("from http://rosalind.info/problems/IIISUM/")
    println("==========================")

    val data = File.fromData(this)

    def run(a: Array[Int], index: Int): (Int, Int, Int) = {
      val m = mutable.HashMap[Int, Int]()
      for (i <- 0 until a.length)  if (!m.contains(a(i))) m += (a(i) -> i)
      val m2 = m.map(_ swap)
      val l = m.size - 1
      val e = m2.keys.toArray.sorted
      val cnt = math.pow(l, 3).toLong
      println(s"start >>> $index: $l, $cnt")
      for (i <- 0 to l; ai = m2(e(i));
           j <- i+1 to l; aj = m2(e(j));
           k <- j+1 to l) {
        val ak = m2(e(k))
        if (ai + aj + ak == 0) return (e(i)+1,e(j)+1,e(k)+1)
      }
      println(s"finish >>> $index")
      (-1, -1, -1)
    }

    val str = new Array[String](data.length-1)
    data.drop(1).map(_ split " " map(_ toInt)).zipWithIndex.par.foreach(el => {
      run(el._1, el._2) match {
        case (-1, -1, -1) => str(el._2) = "-1"
        case Tuple3(i, j, k) => str(el._2) = i + " " + j + " " + k
      }
    })
    File.toFile(this, "answer", str.mkString("\n"))
  }
}