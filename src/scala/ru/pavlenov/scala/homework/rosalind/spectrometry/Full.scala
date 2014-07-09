package ru.pavlenov.scala.homework.rosalind.spectrometry

import ru.pavlenov.scala.libs.peptide.AminoAcid
import ru.pavlenov.scala.utils.File

import scala.collection.mutable

/**
 * ⓭ + 27
 * Какой сам? by Pavlenov Semen 07.07.14.
 * Inferring Peptide from Full Spectrum
 * http://rosalind.info/problems/full/
 *
 * Given:
 * A list L containing 2n+3 positive real numbers (n≤100). The first number in L is the parent mass of a peptide P,
 * and all other numbers represent the masses of some b-ions and y-ions of P (in no particular order). You may assume
 * that if the mass of a b-ion is present, then so is that of its complementary y-ion, and vice-versa.
 *
 * Return:
 * A protein string t of length n for which there exist two positive real numbers w1 and w2 such that for every
 * prefix p and suffix s of t, each of w(p)+w1 and w(s)+w2 is equal to an element of L. (In other words, there
 * exists a protein string whose t-prefix and t-suffix weights correspond to the non-parent mass values of L.)
 * If multiple solutions exist, you may output any one.
 */

object Full {

  val mass2char = AminoAcid.getMassChar.map(el => { ( el._1/1000, el._2 ) }).toMap.withDefaultValue('*')
  val char2mass = AminoAcid.getCharMass.map(el => { ( el._1, el._2/1000 ) }).toMap.withDefaultValue(0)

  def start() {

    println("Inferring Protein from Full Spectrum")
    println("from http://rosalind.info/problems/full/")
    println("==========================")

    val data = File.fromData(this).map(_ toDouble)
    val massPeptide = data.head
    var masses = data.tail
    val n = (masses.length / 2) - 1


    var result = mutable.MutableList[Char]()
    var curr = masses(0)

    while (result.length < n) {

      val c = find(curr, masses)
      if (c != '*') {
        result += c
        curr += char2mass(c)
        masses = masses.filter(c => { c - curr > 0 })

      }

    }

    println(result.mkString)

    println("KEKEP")

  }

  def diff(m1: Double, m2: Double) = math.abs(((m1 - m2) * 10000000).toInt / 100000)

  def find(m1: Double, masses: Array[Double]): Char = {
    for (m2 <- masses) {
      val c: Char = mass2char(diff(m1, m2))
      if (c != '*') return c
    }
    '*'
  }

}
