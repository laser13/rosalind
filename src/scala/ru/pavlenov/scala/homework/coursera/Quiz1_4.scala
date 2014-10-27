package ru.pavlenov.scala.homework.coursera

import ru.pavlenov.scala.libs.genome.DNA

/**
 * ⓭ + 57
 * Какой сам? by Pavlenov Semen 24.10.14.
 */
object Quiz1_4 {

  /**
   * What is the reverse complement of GATTACA?
   */
  def start(): Unit = {
    val text = "GATTACA"
    println(DNA.reverse(text))
  }

}
