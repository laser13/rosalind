package ru.pavlenov.scala.homework.rosalind.ngs

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 39
 * Какой сам? by Pavlenov Semen 24.08.14.
 * Read Quality Distribution 
 * http://rosalind.info/problems/Phre/
 *
 * Given:
 * A quality threshold, along with FASTQ entries for multiple reads.
 *
 * Return:
 * The number of reads whose average quality is below the threshold.
 */

object Phre {

  def start() {

    println("Read Quality Distribution ")
    println("from http://rosalind.info/problems/Phre/")
    println("see http://en.wikipedia.org/wiki/FASTQ_format")
    println("==========================")

    val data = File.fromData(this)

    val delta = 33
    val threshold = data.head.toInt
    val list = data.tail

    var count = 0
    list.sliding(4, 4).toList.map(el => {
      val ch = el(3)
      val avg = ch.map(c => c.toByte - delta).sum / ch.length
      if (avg < threshold) count += 1
    })

    println(count)


  }

}