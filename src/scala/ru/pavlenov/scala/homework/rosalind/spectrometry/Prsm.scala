package ru.pavlenov.scala.homework.rosalind.spectrometry

import ru.pavlenov.scala.libs.peptide.AminoAcid
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 14
 * Какой сам? by Pavlenov Semen 15.07.14.
 * Matching a Spectrum to a Protein
 * http://rosalind.info/problems/prsm/
 *
 * Given:
 * A positive integer n followed by a collection of n protein strings s1, s2, ..., sn and a multiset R of positive numbers (corresponding to the complete spectrum of some unknown protein string).
 *
 * Return:
 * The maximum multiplicity of R⊖S[sk] taken over all strings sk, followed by the string sk for which this maximum multiplicity occurs (you may output any such value if multiple solutions exist).
 */

object Prsm {

  def start() {

    println("Matching a Spectrum to a Protein")
    println("from http://rosalind.info/problems/prsm/")
    println("==========================")

    val data = File.fromData(this)
    val n = data(0).toInt
    val aa = new Array[AminoAcid](n)
    val masses = new Array[Double](data.length-n-1)
    for (i <- 1 to n) aa(i-1) = AminoAcid(data(i))
    for (i <- n+1 until data.length ) masses(i-n-1) = data(i).toDouble

//    println(n)
//    println(aa.mkString(", "))
//    println(masses.mkString(", "))

    var max = 0
    var str = ""

    for (a <- aa) {

      val cs = a.completeSpectrum()
      val md = AminoAcid.MinkowskiDiff(masses, cs)

      val m = md.groupBy(c => c.toUpperCase).map(e => (e._1, e._2.length)).maxBy(e => e._2)

      if (m._2 >= max) { max = m._2; str = a.toString }

    }

    println(max)
    println(str)

  }

}