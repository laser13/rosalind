package ru.pavlenov.scala.homework.rosalind.probability

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 48
 * Какой сам? by Pavlenov Semen 06.08.14.
 * Independent Segregation of Chromosomes
 * http://rosalind.info/problems/Indc/
 *
 * Given:
 * A positive integer n≤50.
 *
 * Return:
 * An array A of length 2n in which A[k] represents the common logarithm of the probability that two diploid siblings share at least k of their 2n chromosomes (we do not consider recombination for now).
 */

object Indc {

  def start() {

    println("Independent Segregation of Chromosomes")
    println("from http://rosalind.info/problems/Indc/")
    println("==========================")

    val data = File.fromData(this)

  }

}