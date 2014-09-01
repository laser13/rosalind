package ru.pavlenov.scala.homework.rosalind.ngs

import ru.pavlenov.scala.utils.File

import scala.collection.mutable.ArrayBuffer

/**
 * ⓭ + 22
 * Какой сам? by Pavlenov Semen 24.08.14.
 * Base Quality Distribution 
 * http://rosalind.info/problems/Bphr/
 *
 * Given:
 * FASTQ file, quality threshold q
 *
 * Return:
 * Number of positions where mean base quality falls below given threshold
 */

object Bphr {

  def start() {

    println("Base Quality Distribution ")
    println("from http://rosalind.info/problems/Bphr/")
    println("==========================")

    val data = File.fromData(this)
    val delta = 33
    val threshold = data.head.toInt
    val list = data.tail

    val line = list.length / 4
    val scores = new Array[Int](list(1).length)

    list.sliding(4, 4).toList.map(el => {
      val ch = el(3).map(c => c.toByte - delta).toList
      var i = 0; for (c <- ch) { scores(i) += c; i += 1 }
    })

    var cnt = 0
    scores.map(_ / line).foreach(el => {
      if (el < threshold) cnt += 1
    })

    println(cnt)

  }

}