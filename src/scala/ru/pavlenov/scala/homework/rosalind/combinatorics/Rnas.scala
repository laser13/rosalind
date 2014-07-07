package ru.pavlenov.scala.homework.rosalind.combinatorics

import scala.collection.mutable

/**
 * ⓭ + 03
 * Какой сам? by Pavlenov Semen 06.07.14.
 * Wobble Bonding and RNA Secondary Structures 
 * http://rosalind.info/problems/rnas/
 *
 * Given:
 * An RNA string s (of length at most 200 bp).
 *
 * Return:
 * The total number of distinct valid matchings of basepair edges in the bonding graph of s. Assume that wobble base pairing is allowed.
 */

object Rnas {

  def start() {

    println("Wobble Bonding and RNA Secondary Structures ")
    println("from http://rosalind.info/problems/rnas/")
    println("==========================")

    val text = "UACCCUGCGUUAUUAACCCGCUACGAUGACCAAUUUGACAAGCCACAUUCACUGUAUUAAUUUGCACCCUUGGCGCCAAAACCCACCCGGUGCAACAGCAGUGACCGUUAGGUCGGAUGUCAGAGUUUUGAUCAUAAUGUGUGGCGCAGAUGUGAUUGCUACACCUGUUUCA"

    val result = findMotzkin(text)
    println("Result: " + result)
    println("Modulo: " + (result % 1000000))


  }

  def findMotzkin(text: String):BigInt = {

    val bonding = Map[Char, Char]('A' -> 'U', 'U' -> 'A', 'C' -> 'G', 'G' -> 'C')
    var map = Map[String, BigInt]()

    def isWobble(c1: Char, c2: Char): Boolean = {
      (c1 == 'U' && c2 == 'G') || (c1 == 'G' && c2 == 'U')
    }

    def calc(text: String):BigInt = {

      if (text.length <= 4) return 1
      if (map.contains(text)) return map(text)


      val indeces = mutable.MutableList[Int]()
      (4 until text.length).foreach(i => {
        if (text(0) == bonding(text(i)) || isWobble(text(0), text(i))) {
          indeces += i
        }
      })

      var count = BigInt(0)
      indeces.foreach(i => {
        count += (calc(text.substring(1, i)) * calc(text.substring(i + 1)))
      })
      count += calc(text.substring(1))
      map += (text -> count)
      map(text)

    }

    calc(text)


  }

}