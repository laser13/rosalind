package ru.pavlenov.scala.utils

/**
 * ⓭ + 38
 * Какой сам? by Pavlenov Semen 05.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */
object Base {

  def timed[T](f: => T) = {
    val start = System.nanoTime
    val r = f
    val stop = System.nanoTime
    println()
    println("__________________________")
    println("Elapsed time: " + (stop - start) / 1000000f + " ms.")
    r
  }

}
