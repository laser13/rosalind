package ru.pavlenov.scala.homework.rosalind.combinatorics

import ru.pavlenov.bio.algorithm.ReversalSort
import ru.pavlenov.scala.utils.{Comb, File}
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

/**
 * ⓭ + 35
 * Какой сам? by Pavlenov Semen 31.08.14.
 * Reversal Distance
 * http://rosalind.info/problems/rear/
 *
 * A reversal of a permutation creates a new permutation by inverting some interval of the permutation;
 * (5,2,3,1,4), (5,3,4,1,2), and (4,1,2,3,5) are all reversals of (5,3,2,1,4). The reversal distance between
 * two permutations π and σ, written drev(π,σ), is the minimum number of reversals required to transform π into σ
 * (this assumes that π and σ have the same length).
 *
 * Given:
 * A collection of at most 5 pairs of permutations, all of which have length 10.
 *
 * Return:
 * The reversal distance between each permutation pair.
 */

object Rear {

  def start() {

    val t1 = "Reversal Distance"
    val t2 = "from http://rosalind.info/problems/rear/"
    println(t1 + "\n" + t2)
    println("="*t2.length)

    val data = File.fromData(this).map(_ split " ")

    data.sliding(2,2).toList.map(el => {

      val java1: Array[java.lang.Integer] = el(0) map(_ toInt) map java.lang.Integer.valueOf
      val java2: Array[java.lang.Integer] = el(1).map(_ toInt) map java.lang.Integer.valueOf
//      ReversalSort.greedySorting(java1)
//      ReversalSort.greedySorting(java2)

      println(Comb.reversalDistance(el(0).map(_ toInt), el(1).map(_ toInt)))

      println("*"*13)
    })

  }
}
