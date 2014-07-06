package ru.pavlenov.scala.homework.rosalind.probability

import ru.pavlenov.scala.utils.{File, Prob, Str}


/**
 * ⓭ + 19
 * Какой сам? by Pavlenov Semen 04.07.14.
 *
 * Expected Number of Restriction Sites
 * http://rosalind.info/problems/eval/
 *
 * Given: A positive integer n (n≤1,000,000), a DNA string s of even length at most 10, and an
 * array A of length at most 20, containing numbers between 0 and 1.
 *
 * Return: An array B having the same length as A in which B[i] represents the expected number of times
 * that s will appear as a substring of a random DNA string t of length n, where t is formed
 * with GC-content A[i] (see “Introduction to Random Strings”).
 *
 */
object Eval {

  def start() {

    println("Start Eval")

    val data = File.fromData(this.getClass.getName) split "\\n"

    val n = data(0).toInt
    val text = data(1)
    val GCContents: Array[Double] = data(2) split " " map (_.toDouble)

    val counts = Str.counts(text)

    var r = Array[Float](GCContents.length)

    GCContents foreach { i =>

      val p = Prob.gcContent(counts, i)
      val r = math.exp(p) * (n - 1 + text.length)
      print("%.6f " format r replace(",", "."))

    }

  }

}
