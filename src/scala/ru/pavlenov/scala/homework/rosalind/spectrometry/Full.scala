package ru.pavlenov.scala.homework.rosalind.spectrometry

import ru.pavlenov.scala.libs.peptide.AminoAcid
import ru.pavlenov.scala.utils.File

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

  def start() {

    println("Inferring Protein from Spectrum")
    println("from http://rosalind.info/problems/spec/")
    println("==========================")

    val mass = File.fromData(this).map(_ toFloat)


    println("KEKEP")

  }

}
