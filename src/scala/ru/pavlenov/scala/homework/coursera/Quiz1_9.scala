package ru.pavlenov.scala.homework.coursera

import ru.pavlenov.scala.libs.genome.Kmer

/**
 * ⓭ + 40
 * Какой сам? by Pavlenov Semen 25.10.14.
 */
object Quiz1_9 {

  def start() = {
    val kmer = "TACAGAGCTTTT"
    val d = 2

    println(Kmer.neighbors(kmer, d).sorted.mkString("\n"))
  }

}
