package ru.pavlenov.scala.homework.rosalind.string_algorithms

import ru.pavlenov.scala.libs.algorithm.string.Common
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 30
 * Какой сам? by Pavlenov Semen 26.07.14.
 * Finding Disjoint Motifs in a Gene 
 * http://rosalind.info/problems/itwv/
 *
 * Given:
 * A text DNA string s of length at most 10 kbp, followed by a collection of n (n≤10) DNA strings of length at most 10 bp acting as patterns.
 *
 * Return:
 * An n×n matrix M for which Mj,k=1 if the jth and kth pattern strings can be interwoven into s and Mj,k=0 otherwise.
 */

object Itwv {

  def start() {

    println("Finding Disjoint Motifs in a Gene ")
    println("from http://rosalind.info/problems/itwv/")
    println("==========================")

    val data = File.fromData(this)
    val (haystack, motifs) = (data.head, data.tail)

    for (m1 <- motifs) {
      val row = motifs.map( m2 =>
        if (Common.mix(m1,m2).distinct.exists(haystack contains _)) "1" else "0"
      ).mkString(" ")
      println(row)
    }
  }

}