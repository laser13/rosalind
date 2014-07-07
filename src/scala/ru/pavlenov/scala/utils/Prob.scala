package ru.pavlenov.scala.utils

/**
 * ⓭ + 38
 * Какой сам? by Pavlenov Semen 04.07.14.
 */
object Prob {

  /**
   *
   *
   * @param text
   * @param gc
   * @return
   */
  def gcContent(text: String, gc: Double): Double = {
    val counts = Str.counts(text)
    gcContent(counts, gc)
  }

  def gcContent(counts: Map[Char, Int], gc: Double): Double = {

    val probAT = math.log((1 - gc) / 2)
    val probGC = math.log(gc / 2)

    val countAT = counts('A') + counts('T')
    val countGC = counts('G') + counts('C')

    probAT * countAT + probGC * countGC

  }

}
