package ru.pavlenov.scala.homework.rosalind.genome_assembly

import ru.pavlenov.bio.assemble.Base
import ru.pavlenov.scala.utils.File
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

/**
 * ⓭ + 28
 * Какой сам? by Pavlenov Semen 31.08.14.
 * Genome Assembly with Perfect Coverage 
 * http://rosalind.info/problems/Pcov/
 *
 * Given:
 * A collection of (error-free) DNA k-mers (k≤50) taken from the same strand of a circular chromosome. In this dataset, all k-mers from this strand of the chromosome are present, and their de Bruijn graph consists of exactly one simple cycle.
 *
 * Return:
 * A cyclic superstring of minimal length containing the reads (thus corresponding to a candidate cyclic chromosome).
 */

object Pcov {

  def start() {

    println("Genome Assembly with Perfect Coverage ")
    println("from http://rosalind.info/problems/Pcov/")
    println("==========================")

    val data = File.fromData(this)
    val superstring = Base.getShortestSuperstring(data, data(0).length-1)

    println(superstring)

  }

}