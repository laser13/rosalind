package ru.pavlenov.scala.homework.rosalind.combinatorics

import ru.pavlenov.scala.utils.File

import scala.collection.mutable

/**
 * ⓭ + 52
 * Какой сам? by Pavlenov Semen 05.07.14.
 * Catalan Numbers and RNA Secondary Structures
 * http://rosalind.info/problems/cat/
 *
 * Given:
 * An RNA string s having the same number of occurrences of 'A' as 'U' and the same number of occurrences of 'C' as 'G'. The length of the string is at most 300 bp.
 *
 * Return:
 * The total number of noncrossing perfect matchings of basepair edges in the bonding graph of s, modulo 1,000,000.
 */
object Cat {

  def start() {

    println("Catalan Numbers and RNA Secondary Structures")
    println("from http://rosalind.info/problems/cat/")
    println("==========================")

    val text = File.readFasta(this.getClass.getName)(0)._2
    val result = countNonCrossing(text)
    println("Given: " + text)
    println("Result: " + result)
    println("Modulo: " + result % 1000000)

  }

  /**
   * Ищем кол-во всех возможных наборов из непересекающихся рёбер
   * C[n] = [k=1..n]∑ C[k−1]⋅C[n−k]
   *
   * @param text
   * @return
   */
  def countNonCrossing(text: String): BigInt = {

    var map = Map[String, BigInt]()

    def calc(text: String): BigInt = {
      if (text.length <= 2) return 1
      else if (map.contains(text)) return map(text)

      val indeces = findSliceLine(text)
      var count = BigInt(0)
      indeces.foreach(i => {
        count += calc(text.substring(1, i)) * calc(text.substring(i + 1))
      })

      map += (text -> count)

      map(text)
    }

    calc(text)

  }

  /**
   * Ищем индексы всех нод к которым можно провести ребро и разбить круг так чтобы в подпоследовательностях было равное кол-во A - U и G - C
   *
   * @param text
   * @return
   */
  def findSliceLine(text: String): mutable.MutableList[Int] = {
    val bonding = Map[Char, Char]('A' -> 'U', 'U' -> 'A', 'C' -> 'G', 'G' -> 'C')
    val indeces = mutable.MutableList[Int]()
    (1 to text.length - 1 by 2).foreach(i => {
      if (text(0) == bonding(text(i)) && checkSub(text.substring(1, i))) {
        indeces += i
      }
    })
    indeces
  }

  /**
   * Проверям равное ли кол-во A - U и G - C в строке
   *
   * @param sub
   * @return
   */
  def checkSub(sub: String): Boolean = {
    sub.count(_ == 'A') == sub.count(_ == 'U') && sub.count(_ == 'G') == sub.count(_ == 'C')
  }

}