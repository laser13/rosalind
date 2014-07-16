package ru.pavlenov.scala.homework.rosalind.spectrometry

import ru.pavlenov.scala.utils.File

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * ⓭ + 44
 * Какой сам? by Pavlenov Semen 14.07.14.
 * Comparing Spectra with the Spectral Convolution
 * http://rosalind.info/problems/conv/
 *
 * Given:
 * Two multisets of positive real numbers S1 and S2. The size of each multiset is at most 200.
 *
 * Return:
 * The largest multiplicity of S1⊖S2, as well as the absolute value of the number x maximizing (S1⊖S2)(x) (you may return any such value if multiple solutions exist).
 */

object Conv {

  def start() {

    println("Comparing Spectra with the Spectral Convolution")
    println("from http://rosalind.info/problems/conv/")
    println("==========================")

    def fix(s: String): Long = {
      val g = s.split("\\.")
      g(0).toLong * math.pow(10, g(1).length).toLong + g(1).toLong
    }

    def unfix(v: Long, d: Int = 5): (Long, Long) = {
      val f = math.pow(10, d).toLong
      val o = v % f
      ((v - o)/f, o)
    }

    val data = File.fromData(this)
    val S1: Array[Double] = data(0).split("\\s").map(_ toDouble)
    val S2: Array[Double] = data(1).split("\\s").map(_ toDouble)
    var Sdiff = mutable.HashMap[String, Int]()

    var cnt = 0
    for (s1 <- S1; s2 <- S2) {
      cnt += 1
      var diff = "%.5f".format(s1 - s2)
      if (Sdiff.contains(diff)) Sdiff(diff) += 1
      else Sdiff += (diff -> 1)

    }

    println(S1.length)
    println(S2.length)
    println(Sdiff.size)
    println(cnt)

    var maxVal = 0l
    var maxKey = ""
    for ((k, v) <- Sdiff) {
      if (maxVal < v) { maxVal = v; maxKey = k }
    }

//    val key = unfix(maxKey.toInt)
//
//
    println(maxVal)
//    println(key._1 + "." + "%05d".format(key._2))


  }

}