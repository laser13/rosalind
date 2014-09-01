package ru.pavlenov.scala.homework.rosalind.set

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 58
 * Какой сам? by Pavlenov Semen 31.08.14.
 * Introduction to Set Operations
 * http://rosalind.info/problems/Seto/
 *
 * Given:
 * A positive integer n (n≤20,000) and two subsets A and B of {1,2,…,n}.
 *
 * Return:
 * Six sets: A∪B, A∩B, A−B, B−A, Ac, and Bc (where set complements are taken with respect to {1,2,…,n}).
 */

object Seto {

  def start() {

    println("Introduction to Set Operations")
    println("from http://rosalind.info/problems/Seto/")
    println("==========================")

    val data = File.fromData(this)

    val n = data.head.toInt
    val a = data(1).substring(1, data(1).length-1).split(", ").map(_ toInt).toSet[Int]
    val b = data(2).substring(1, data(2).length-1).split(", ").map(_ toInt).toSet[Int]
    val c = (1 to n).toSet[Int]

    def pr(set: Set[Int]) = println(set.mkString("{", ", ", "}"))

    // A ∪ B
    pr(a ++ b)
    // A ∩ B
    pr(a & b)
    // A−B
    pr(a &~ b)
    // B-A
    pr(b &~ a)
    // A^c^
    pr(c -- a)
    // B^c^
    pr(c -- b)

  }

}