package ru.pavlenov.scala.homework.rosalind.combinatorics

import ru.pavlenov.scala.utils.File

import scala.collection.mutable

/**
 * ⓭ + 11
 * Какой сам? by Pavlenov Semen 06.07.14.
 * Motzkin Numbers and RNA Secondary Structures 
 * http://rosalind.info/problems/motz/
 *
 * Given:
 * An RNA string s of length at most 300 bp.
 *
 * Return:
 * The total number of noncrossing matchings of basepair edges in the bonding graph of s, modulo 1,000,000.
 */

object Motz {

  def start() {

    println("Motzkin Numbers and RNA Secondary Structures ")
    println("from http://rosalind.info/problems/motz/")
    println("==========================")

    val text = File.readFasta(this.getClass.getName)(0)._2

    val result = findMotzkin(text)
    println("Result: " + result)
    println("Modulo: " + (result % 1000000))


  }

  def findMotzkin(text: String):BigInt = {

    val bonding = Map[Char, Char]('A' -> 'U', 'U' -> 'A', 'C' -> 'G', 'G' -> 'C')
    var map = Map[String, BigInt]()

    def calc(text: String):BigInt = {

      if (text.length <= 1) return 1
      if (map.contains(text)) return map(text)


      val indeces = mutable.MutableList[Int]()
      (1 until text.length).foreach(i => {
        if (text(0) == bonding(text(i))) {
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