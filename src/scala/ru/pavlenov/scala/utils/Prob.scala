package ru.pavlenov.scala.utils

/**
 * ⓭ + 38
 * Какой сам? by Pavlenov Semen 04.07.14.
 */
object Prob {

  def gcContent(text: String, gc: Double): Double = {

    val probAT = math.log((1 - gc) / 2)
    val probGC = math.log(gc / 2)

    val countAT = text.count(_ == 'A') + text.count(_ == 'T')
    val countGC = text.count(_ == 'G') + text.count(_ == 'C')

    probAT * countAT + probGC * countGC

  }

  def gcContent(counts: Map[Char, Int], gc: Double): Double = {

    val probAT = math.log((1 - gc) / 2)
    val probGC = math.log(gc / 2)

    val countAT = counts('A') + counts('T')
    val countGC = counts('G') + counts('C')

    probAT * countAT + probGC * countGC

  }

}
