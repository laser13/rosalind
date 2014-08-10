package ru.pavlenov.scala.homework.rosalind.probability

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 15
 * Какой сам? by Pavlenov Semen 09.08.14.
 * Counting Disease Carriers 
 * http://rosalind.info/problems/Afrq/
 *
 * Given:
 * An array A for which A[k] represents the proportion of homozygous recessive individuals for the k-th Mendelian factor in a diploid population. Assume that the population is in genetic equilibrium for all factors.
 *
 * Return:
 * An array B having the same length as A in which B[k] represents the probability that a randomly selected individual carries at least one copy of the recessive allele for the k-th factor.
 */

object Afrq {

  def start() {

    println("Counting Disease Carriers ")
    println("from http://rosalind.info/problems/Afrq/")
    println("==========================")

    val data = File.fromData(this)(0).split(" ").map(_ toFloat).map(p => 2*math.sqrt(p) - p)

    println(data.map("%.3f".format(_).replace(",", ".")).mkString(" "))

  }

}