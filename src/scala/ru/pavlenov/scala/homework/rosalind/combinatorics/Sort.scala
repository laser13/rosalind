package ru.pavlenov.scala.homework.rosalind.combinatorics

import ru.pavlenov.scala.utils.{Comb, File}

/**
 * ⓭ + 35
 * Какой сам? by Pavlenov Semen 31.08.14.
 * Sorting by Reversals
 * http://rosalind.info/problems/sort/
 *
 * A reversal of a permutation can be encoded by the two indices at the endpoints of the interval that it inverts;
 * for example, the reversal that transforms (4,1,2,6,3,5) into (4,1,3,6,2,5) is encoded by [3,5].
 * A collection of reversals sorts π into γ if the collection contains drev(π,γ) reversals,
 * which when successively applied to π yield γ.
 *
 * Given:
 * Two permutations π and γ, each of length 10.
 *
 * Return:
 * The reversal distance drev(π,γ), followed by a collection of reversals sorting π into γ.
 * If multiple collections of such reversals exist, you may return any one.
 */

object Sort {

  def start() {

    val t1 = "Sorting by Reversals"
    val t2 = "from http://rosalind.info/problems/sort/"
    println(t1 + "\n" + t2)
    println("=" * t2.length)

    val data = File.fromData(this).map(_ split " ")

    println(Comb.reversalDistance(data(0).map(_ toInt), data(1).map(_ toInt)))

  }
}
