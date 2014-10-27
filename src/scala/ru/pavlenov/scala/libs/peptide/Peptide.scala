package ru.pavlenov.scala.libs.peptide

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * ⓭ + 16
 * Какой сам? by Pavlenov Semen 21.10.14.
 */
class Peptide(text: String) {

  import Peptide.Spectrum

  def cyclospectrum = Peptide.cyclospectrum(text)

  def linearSpectrum = Peptide.linearSpectrum(text)

  def cyclicSpectrum = Peptide.cyclicSpectrum(text)

  def score(experimental: Spectrum) = Peptide.score(cyclospectrum, experimental)

}

object Peptide {

  type Spectrum = Seq[Int]

  def apply(text: String) = new Peptide(text)

  def score(theoretical: Spectrum, _experimental: Spectrum) = {
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
   *
   * @param peptide
   * @param experimental
   * @return
   */
  def linearScore(peptide: String, experimental: Spectrum) = score(linearSpectrum(peptide), experimental)


  /**
   * Находим теоретический спектр пептида
   * https://stepic.org/lesson/CS-Generating-the-Theoretical-Spectrum-of-a-Peptide-4912/step/2?course=Bioinformatics-Algorithms&unit=1084
   * @param text
   * @return
   */
  def linearSpectrum(text: String): Spectrum = {
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
  def cyclicSpectrum(text: String): Spectrum = {
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

  def cyclospectrum(text: String): Spectrum = {

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

  /*

Trim(Leaderboard, Spectrum, N, AminoAcid, AminoAcidMass)
    for j ← 1 to Leaderboard
        Peptide ← j-th peptide in Leaderboard
        LinearScores(j) ← LinearScore(Peptide, Spectrum)
    sort Leaderboard according to the decreasing order of scores in LinearScores
    sort LinearScores in decreasing order
    for j ← N + 1 to |Leaderboard|
        if LinearScores(j) < LinearScores(N)
            remove all peptides starting from the j-th peptide from Leaderboard
        return Leaderboard
    return Leaderboard

  * */

  def trim(leaderboard: List[String], spectrum: Spectrum, n: Int) = {
    var linearScores = Map[String, Int]()
    for (peptide <- leaderboard) {
      linearScores += peptide -> linearScore(peptide, spectrum)
    }

    val (sortedLeaderboard, sortedLinearScores) = linearScores.toSeq.sortWith((a, b) => a._2 > b._2).toBuffer.unzip

    def cut(j: Int) {
      if (sortedLinearScores(j) < sortedLinearScores(n - 1)) {
        sortedLeaderboard.remove(j, leaderboard.length - j)
      } else cut(j + 1)
    }

    cut(n)

    sortedLeaderboard

  }

}
