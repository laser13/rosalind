package ru.pavlenov.scala.homework.coursera

import ru.pavlenov.scala.libs.genome.Kmer

/**
 * ⓭ + 05
 * Какой сам? by Pavlenov Semen 24.10.14.
 */
object Quiz1_5 {

  def start() = {

    val text1 = "CTTGAAGTGGACCTCTAGTTCCTCTACAAAGAACAGGTTGACCTGTCGCGAAG"
    val text2 = "ATGCCTTACCTAGATGCAATGACGGACGTATTCCTTTTGCCTCAACGGCTCCT"

    println(Kmer.hammingDistance(text1, text2))

  }

}
