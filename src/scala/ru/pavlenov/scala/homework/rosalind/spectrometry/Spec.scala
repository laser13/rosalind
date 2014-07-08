package ru.pavlenov.scala.homework.rosalind.spectrometry

import ru.pavlenov.bio.amino.Codon.Acid
import ru.pavlenov.bio.amino.{Codon, Peptide}
import ru.pavlenov.scala.libs.peptide.AminoAcid
import ru.pavlenov.scala.utils.File
import scala.collection.JavaConversions._

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

    val mass = File.fromData(this).split("\\n").map(_ toFloat).reverse
    var mazz = new Array[Int](mass.length-1)

    println(mass.mkString(", "))

    for (i <- 0 until mass.length-1) {
      mazz(i) = ((mass(i) - mass(i+1)) * 100000).toInt / 100000
    }

    val mass2char = AminoAcid.getMassChar.map(el => { ( el._1/100000, el._2 ) }).toMap.withDefaultValue(0)

    println(mass2char.mkString(" | "))
    println(mazz.mkString(" | "))

    mazz = mazz.reverse

    mazz.foreach(m => {
      print(mass2char(m))
    })
    println()

  }

}