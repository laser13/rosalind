package ru.pavlenov.scala.homework.rosalind.combinatorics

import ru.pavlenov.scala.utils.{Comb, File, Str}

/**
 * ⓭ + 28
 * Какой сам? by Pavlenov Semen 06.07.14.
 * Maximum Matchings and RNA Secondary Structures
 * http://rosalind.info/problems/mmch/
 *
 * Given:
 * An RNA string s of length at most 100.
 *
 * Return:
 * The total possible number of maximum matchings of basepair edges in the bonding graph of s.
 */

object Mmch {

  def start() {

    println("Maximum Matchings and RNA Secondary Structures")
    println("from http://rosalind.info/problems/mmch/")
    println("==========================")

    val text = File.readFasta(this.getClass.getName)(0)._2
    val map = Str.counts(text)
    val countAU = map('A') + map('U')
    val countGC = map('G') + map('C')

    val assignAU = Comb.assign(math.max(map('A'), map('U')), math.min(map('A'), map('U')))
    val assignGC = Comb.assign(math.max(map('G'), map('C')), math.min(map('G'), map('C')))

    println("Given: " + text)
    println("Result: " + (assignAU * assignGC))

  }


}