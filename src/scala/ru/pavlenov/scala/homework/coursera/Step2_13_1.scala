package ru.pavlenov.scala.homework.coursera

import ru.pavlenov.scala.libs.peptide.Peptide
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 20
 * Какой сам? by Pavlenov Semen 23.10.14.
 * Compute the score of a linear peptide with respect to a spectrum.
 * https://stepic.org/lesson/CS-Trimming-the-Peptide-Leaderboard-4913/step/1?course=Bioinformatics-Algorithms&unit=1085
 *
 * Given:
 * An amino acid string Peptide and a collection of integers Spectrum.
 *
 * Return:
 * The linear score of Peptide with respect to Spectrum, LinearScore(Peptide, Spectrum).
 */

object Step2_13_1 {

  def start() {

    println("Compute the score of a linear peptide with respect to a spectrum.")
    println("from https://stepic.org/lesson/CS-Trimming-the-Peptide-Leaderboard-4913/step/1?course=Bioinformatics-Algorithms&unit=1085")
    println("==========================")

    val data = File.fromData(this)
    val text = data(0)
    val spectrum = data(1).split(" ").map(_.toInt).toSeq

    val peptide = Peptide(text)

//    println(spectrum.mkString(" "))
//    println(peptide.linearSpectrum.mkString(" "))
//    println(peptide.cyclicSpectrum.mkString(" "))
//    println(peptide.cyclospectrum.mkString(" "))
    println(Peptide.score(peptide.linearSpectrum, spectrum))
//    println(Peptide.score(peptide.cyclicSpectrum, spectrum))
//    println(Peptide.score(peptide.cyclospectrum, spectrum))


  }

}