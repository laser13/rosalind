package ru.pavlenov.scala.homework.coursera

import ru.pavlenov.scala.libs.peptide.Peptide
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 06
 * Какой сам? by Pavlenov Semen 23.10.14.
 * Implement LinearSpectrum.
 * https://stepic.org/lesson/CS-Generating-the-Theoretical-Spectrum-of-a-Peptide-4912/step/2?course=Bioinformatics-Algorithms&unit=1084
 *
 * Given:
 * An amino acid string Peptide.
 *
 * Return:
 * The linear spectrum of Peptide.
 */

object Step2_11_2 {

  def start() {

    println("Implement LinearSpectrum.")
    println("from https://stepic.org/lesson/CS-Generating-the-Theoretical-Spectrum-of-a-Peptide-4912/step/2?course=Bioinformatics-Algorithms&unit=1084")
    println("==========================")

    val text = "FTGTINPGPNTLGNVMMDEMMDEGSLKRTRCPHDGDMPHTAEPKAAIQA"
    println(Peptide.linearSpectrum(text).mkString(" "))

  }

}