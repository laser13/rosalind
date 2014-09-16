package ru.pavlenov.scala.homework.rosalind.genome_assembly

import ru.pavlenov.bio.assemble.Base
import ru.pavlenov.bio.genome.DNA
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 53
 * Какой сам? by Pavlenov Semen 06.09.14.
 * Genome Assembly Using Reads
 * http://rosalind.info/problems/Gasm/
 *
 * Given:
 * A collection S of (error-free) reads of equal length (not exceeding 50 bp). In this dataset, for some positive integer k, the de Bruijn graph Bk on Sk+1∪Srck+1 consists of exactly two directed cycles.
 *
 * Return:
 * A cyclic superstring of minimal length containing every read or its reverse complement.
 */

object Gasm {

  def start() {

    println("Genome Assembly Using Reads")
    println("from http://rosalind.info/problems/gasm/")
    println("==========================")

    val data = File.fromData(this)

    val kmers = data ++ data.map(k => DNA.reverse(k))

    println(kmers.mkString("\n"))

    kmers.sliding(2).toList.map(e => println(findMaxOverlap(e(0), e(1 ))))

  }

  def findMaxOverlap(read1: String, read2: String, minLenOverlap: Int = 1): String = {

    val len1: Int = read1.length
    val len2: Int = read2.length
    val minLen: Int = Math.min(len1, len2)

    var maxOverlap = ""
    var currLen: Int = minLenOverlap
    while (currLen < minLen) {
      val suffix1 = read1.substring(len1 - currLen, len1)
      val preffix1 = read1.substring(0, currLen)
      val suffix2 = read2.substring(len2 - currLen, len2)
      val preffix2 = read2.substring(0, currLen)
      if (suffix1 == preffix2) maxOverlap = suffix1
      if (suffix2 == preffix1) maxOverlap = suffix2
      currLen += 1
      currLen - 1
    }

    maxOverlap
  }

}