package ru.pavlenov.scala.homework.rosalind.algorithms.string

import ru.pavlenov.scala.libs.algorithm.string.ShortestCommonSupersequence
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 55
 * Какой сам? by Pavlenov Semen 22.07.14.
 * Interleaving Two Motifs
 * http://rosalind.info/problems/scsp/
 *
 * Given:
 * Two DNA strings s and t.
 *
 * Return:
 * A shortest common supersequence of s and t. If multiple solutions exist, you may output any one.
 */

object Scsp {

  def start() {

    println("Interleaving Two Motifs")
    println("from http://rosalind.info/problems/scsp/")
    println("==========================")

    val data = File.fromData(this)
    val strI = data(0)
    val strJ = data(1)

    val scs = ShortestCommonSupersequence(strI, strJ)

    println(scs.find())
    println("GTCGATGAACCATCATCAACTTGGCGACCGTGAGACGCATTGCTTGAAAGCGCTTGACGAGTCATCTAGTCTCACCGACTCACTGATTCGGGACTTTAAGGGATTAGGGTATGATGTGACCGT")

  }

}