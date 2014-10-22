package ru.pavlenov.scala.libs.peptide

import scala.collection.mutable.ArrayBuffer

/**
 * ⓭ + 16
 * Какой сам? by Pavlenov Semen 21.10.14.
 */
class Peptide(text: String) {

  def cyclospectrum = Peptide.cyclospectrum(text)

  def score(experimental: Seq[Int]) = Peptide.score(cyclospectrum, experimental)

}

object Peptide {

  def apply(text: String) = new Peptide(text)

  def score(theoretical: Seq[Int], _experimental: Seq[Int]) = {

    var cnt = 0
    var experimental = _experimental.toBuffer

    for (t <- theoretical) {
      if (experimental.contains(t)) {
        cnt += 1
        experimental -= t
      }
    }

    cnt

  }

  def cyclospectrum(text: String): Seq[Int] = {

    val aa2mass = AminoAcid.aa2mass.map(x => x._1 -> x._2 / 100000).toMap
    val len = text.length
    val sub = ArrayBuffer[String]()
    sub += text

    for (i <- 1 until len) {
      val t0 = text + text.substring(0, i-1)
      for (j <- 0 to t0.length - i) {
        sub += t0.substring(j, j + i)
      }
    }

    var res = Seq[Int](0)
    sub.foreach(x => {
      res :+= x.map(aa2mass).sum
    })

    res.sorted

  }

}
