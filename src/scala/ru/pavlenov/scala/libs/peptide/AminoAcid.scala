package ru.pavlenov.scala.libs.peptide

import ru.pavlenov.scala.utils.Str

/**
 * ⓭ + 44
 * Какой сам? by Pavlenov Semen 07.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */
class AminoAcid(val aa: String) {

  val len = aa.length

  /**
   * The complete spectrum of a weighted string is the collection of all its prefix and suffix weights.
   */
  def completeSpectrum():Array[Double] = {

    val arr = new Array[String](2*len-1)
    arr(0) = aa
    for (i <- 1 until len) {
      arr(i) = Str.pref(aa, i)
      arr(len+i-1) = Str.suff(aa, i)
    }
    arr.map(AminoAcid.aa2spectrum)
  }

  override def toString = aa

}

object AminoAcid {

  def apply(aa: String) = new AminoAcid(aa)

  val aa = Array(
    ('A', "Ala", "Alanine",       "C3H5NO",   7103711,  71.03711d,  Array("GCA", "GCU", "GCC", "GCG")),
    ('C', "Cys", "Cysteine",      "C3H5NOS",  10300919, 103.00919d, Array("UGC", "UGU")),
    ('D', "Asp", "Aspartic acid", "C4H5NO3",  11502694, 115.02694d, Array("GAU", "GAC")),
    ('E', "Glu", "Glutamic acid", "C5H7NO3",  12904259, 129.04259d, Array("GAA", "GAG")),
    ('F', "Phe", "Phenylalanine", "C9H9NO",   14706841, 147.06841d, Array("UUC", "UUU")),
    ('G', "Gly", "Glycine",       "C2H3NO",   5702146,  57.02146d,  Array("GGA", "GGU", "GGC", "GGG")),
    ('H', "His", "Histidine",     "C6H7N3O",  13705891, 137.05891d, Array("GAU", "GAC")),
    ('I', "Ile", "Isoleucine",    "C6H11NO",  11308406, 113.08406d, Array("AUU", "AUC", "AUA")),
    ('K', "Lys", "Lysine",        "C6H12N2O", 12809496, 128.09496d, Array("AAA", "AAG")),
    ('L', "Leu", "Leucine",       "C6H11NO",  11308406, 113.08406d, Array("UUG", "UUA", "CUA", "CUU", "CUC", "CUG")),
    ('M', "Met", "Methionine",    "C5H9NOS",  13104049, 131.04049d, Array("AUG")),
    ('N', "Asn", "Asparagine",    "C4H6N2O2", 11404293, 114.04293d, Array("AAC", "AAU")),
    ('P', "Pro", "Proline",       "C5H7NO",   9705276,  97.05276d,  Array("CCA", "CCU", "CCC", "CCG")),
    ('Q', "Gln", "Glutamine",     "C5H8N2O",  12805858, 128.05858d, Array("CAA", "CAG")),
    ('R', "Arg", "Arginine",      "C6H12N4O", 15610111, 156.10111d, Array("AGA", "AGG", "CGA", "CGU", "CGC", "CGG")),
    ('S', "Ser", "Serine",        "C3H5NO2",  8703203,  87.03203d,  Array("AGU", "AGC", "UCU", "UCC", "UCA", "UCG")),
    ('T', "Thr", "Threonine",     "C4H7NO2",  10104768, 101.04768d, Array("ACA", "ACU", "ACC", "ACG")),
    ('V', "Val", "Valine",        "C5H9NO",   9906841,  99.06841d,  Array("GUA", "GUU", "GUC", "GUG")),
    ('W', "Trp", "Tryptophan",    "C11H10N20",18607931, 186.07931d, Array("UGG")),
    ('Y', "Tyr", "Tyrosine",      "C9H9NO2",  16306333, 163.06333d, Array("UAC", "UAU")),
    ('*', "Xxx", "Stop",          null,       0,        0.0d,       Array("UGA", "UAG", "UAA"))
  )

  def aa2mass = aa.map(el => (el._1, el._5))

  def mass2aa = aa.map(el => (el._5, el._1))

  def mass5aa: Map[Int,Char] = mass2aa.map(el => { ( el._1/100, el._2 ) }).toMap.withDefaultValue('*')

  def aa5imass: Map[Char,Int] = mass2aa.map(el => { ( el._2, el._1/100 ) }).toMap.withDefaultValue(0)

  def aa2dmass: Map[Char,Double] = aa.map(el => (el._1, el._6)).toMap.withDefaultValue(0.0d)

  def round5(m: Double): Int = (m * 100000).toInt / 1000

  def round(m: Double, p: Int): Int = (m * 100000).toInt / 1000

  def diff(m1: Double, m2: Double): Int = math.abs(round5(m1 - m2))

  /**
   * Берёт последовательность масс (разница между соседями должна равнятся ~ массе некоторой аминокислоты)
   * и отдаём последовательность аминокислот
   *
   * @param masses
   * @return
   */
  def spectro2aa(masses: List[Double]) = {
    var aa = List[Char]()
    val m5a = mass5aa
    for (i <- 0 to masses.length-2) {
      aa = aa :+ m5a(diff(masses(i), masses(i+1)))
    }; aa
  }

  def aa2spectrum(aa: String): Double = aa.map(aa2dmass(_)).sum

  def MinkowskiDiff(S1: Array[Double], S2: Array[Double]): Array[String] = {
    val Sdiff = new Array[String](S1.length*S2.length)
    var i: Int = 0
    for (s1 <- S1; s2 <- S2) {
      var diff = "%.5f".format(math.abs(s1 - s2))
      Sdiff(i) = diff
      i += 1
    }
    Sdiff
  }

}
