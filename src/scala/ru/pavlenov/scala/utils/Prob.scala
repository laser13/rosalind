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

  /**
   * Испытания Бернулли
   * @param k число
   * @param n число испытаний
   */
  def binom(n: Int, k: Int, p: Double = 0.5): Double = Comb.choose(n, k).toDouble * math.pow(p, k) * math.pow(1-p, n-k)

  def log10binom(n: Int, k: Int, p: Double): Double = {
    val c = Comb.choose(n, k)
    (math.log10(p) * k) + (math.log10(1-p) * (n-k)) + math.log10(c.toDouble)
  }

}
