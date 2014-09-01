package ru.pavlenov.scala.homework.rosalind.set

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 17
 * Какой сам? by Pavlenov Semen 31.08.14.
 * Creating a Restriction Map
 * http://rosalind.info/problems/Pdpl/
 *
 * Given:
 * A multiset L containing (n2) positive integers for some positive integer n.
 *
 * Return:
 * A set X containing n nonnegative integers such that ΔX=L.
 */

object Pdpl {

  def start() {

    println("Creating a Restriction Map")
    println("from http://rosalind.info/problems/Pdpl/")
    println("==========================")

    val data = "2 2 3 3 4 5 6 7 8 10".split(" ").map(_ toInt)

    var res = Set[Int]()

    for (x <- data; y <- data) {
      res = res + math.abs(x - y)
    }

    println(data.toSet, res)


  }

}