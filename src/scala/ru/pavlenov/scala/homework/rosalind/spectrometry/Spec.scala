package ru.pavlenov.scala.homework.rosalind.spectrometry

import ru.pavlenov.scala.libs.peptide.AminoAcid
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 27
 * Какой сам? by Pavlenov Semen 07.07.14.
 * Inferring Protein from Spectrum
 * http://rosalind.info/problems/spec/
 *
 * Given:
 * A list L of n (n≤100) positive real numbers.
 *
 * Return:
 * A protein string of length n−1 whose prefix spectrum is equal to L (if multiple solutions exist, you may output any one of them). Consult the monoisotopic mass table.
 */

object Spec {

  def start() {

    println("Inferring Protein from Spectrum")
    println("from http://rosalind.info/problems/spec/")
    println("==========================")

    val mass = File.fromData(this).map(_ toFloat).reverse
    val mazz = new Array[Int](mass.length - 1)

    for (i <- 0 until mass.length-1) {
      mazz(mass.length-2-i) = ((mass(i) - mass(i+1)) * 10000000).toInt / 100000
    }

    val mass2char = AminoAcid.getMassChar.map(el => { ( el._1/1000, el._2 ) }).toMap.withDefaultValue('#')

    mazz.foreach(m => {
      print(mass2char(m))
    })
    println()

  }

}