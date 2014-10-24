package ru.pavlenov.scala.libs.peptide

import scala.collection.mutable.ArrayBuffer

/**
 * ⓭ + 16
 * Какой сам? by Pavlenov Semen 21.10.14.
 */
class Peptide(text: String) {

  def cyclospectrum = Peptide.cyclospectrum(text)

  def linearSpectrum = Peptide.linearSpectrum(text)

  def cyclicSpectrum = Peptide.cyclicSpectrum(text)

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


  /**
   * Находим теоретический спектр пептида
   * https://stepic.org/lesson/CS-Generating-the-Theoretical-Spectrum-of-a-Peptide-4912/step/2?course=Bioinformatics-Algorithms&unit=1084
   * @param text
   * @return
   */
  def linearSpectrum(text: String): Seq[Int] = {
    val aa2mass = AminoAcid.aa2mass.map(x => x._1 -> x._2 / 100000).toMap
    var prefixMass = Map[Int, Int](0 -> 0)
    for (i <- 0 until text.length) {
      val aa = text(i)
      prefixMass += (i + 1) -> (prefixMass(i) + aa2mass(aa))
    }

    var spectrum = List(0)
    for (i <- 0 until text.length; j <- i+1 to text.length) {
      spectrum :+= prefixMass(j) - prefixMass(i)
    }
    spectrum.sorted
  }

  /**
   * Находим теоретический циклический спектр
   * https://stepic.org/lesson/CS-Generating-the-Theoretical-Spectrum-of-a-Peptide-4912/step/3?course=Bioinformatics-Algorithms&unit=1084
   * @param text
   * @return
   */
  def cyclicSpectrum(text: String): Seq[Int] = {
    val aa2mass = AminoAcid.aa2mass.map(x => x._1 -> x._2 / 100000).toMap
    var prefixMass = Map[Int, Int](0 -> 0)
    for (i <- 0 until text.length) {
      val aa = text(i)
      prefixMass += (i + 1) -> (prefixMass(i) + aa2mass(aa))
    }

    val peptideMass = prefixMass(text.length)
    var spectrum = List(0)
    for (i <- 0 until text.length; j <- i+1 to text.length) {
      val diff = prefixMass(j) - prefixMass(i)
      spectrum :+= diff
      spectrum :+= peptideMass - diff
    }
    spectrum.sorted
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
    sub.foreach(res :+= _.map(aa2mass).sum)
    res.sorted
  }

}
