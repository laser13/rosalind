package ru.pavlenov.scala.homework.rosalind.spectrometry

import ru.pavlenov.scala.libs.peptide.AminoAcid
import ru.pavlenov.scala.utils.File

import scala.collection.mutable.ArrayBuffer

/**
 * ⓭ + 27
 * Какой сам? by Pavlenov Semen 07.07.14.
 * Inferring Peptide from Full Spectrum
 * http://rosalind.info/problems/full/
 *
 * Given:
 * A list L containing 2n+3 positive real numbers (n≤100). The first number in L is the parent mass of a peptide P,
 * and all other numbers represent the masses of some b-ions and y-ions of P (in no particular order). You may assume
 * that if the mass of a b-ion is present, then so is that of its complementary y-ion, and vice-versa.
 *
 * Return:
 * A protein string t of length n for which there exist two positive real numbers w1 and w2 such that for every
 * prefix p and suffix s of t, each of w(p)+w1 and w(s)+w2 is equal to an element of L. (In other words, there
 * exists a protein string whose t-prefix and t-suffix weights correspond to the non-parent mass values of L.)
 * If multiple solutions exist, you may output any one.
 */

object Full {

  // Карта соотношений [вес аминокислоты -> её символьное обозначение], вес с точностью до 2 знака
  val mass2aa = AminoAcid.getMassChar.map(el => { ( el._1/1000, el._2 ) }).toMap.withDefaultValue('*')

  def start() {

    println("Inferring Protein from Full Spectrum")
    println("from http://rosalind.info/problems/full/")
    println("==========================")

    val data = File.fromData(this).map(_ toDouble)

    // Масса целого пептида
    val massPeptide = round(data.head)

    // Массив масс b-ion & y-ion
    var masses = data.tail

    // Длина икомого куска белка
    val n = (masses.length / 2) - 1

    // Массив парных кусков
    var pairs = ArrayBuffer[Array[Double]]()

    // Нужно найти парные куски (в сумме должны составлять всю массу пептида)
    var not = ArrayBuffer[Int]()
    for (i <- 0 until masses.length; j <- 0 until masses.length if !not.contains(j) ) {
      if (round(masses(i) + masses(j)) == massPeptide) {
        pairs += Array(masses(i), masses(j))
        not += (i, j)
      }
    }

    // Сюда будем складывать искомый белок
    var result = ArrayBuffer[Char]()

    // Берём самый маленький кусочек (ну и соответственно в связке с самым большим)
    var _curr = pairs(0)
    while (pairs.nonEmpty) {

      var f = true; var curr = _curr
      for (pair <- pairs) {

        val m = curr(0); val m1 = pair(0); val m2 = pair(1)
        val d1 = diff(m, m1); val d2 = diff(m, m2)

        if (f) {

          // Если разница между текущим куском и каким-нибудь из перебора состовляет осмысленную аминокислоту,
          // то запишем её, текущий кусок удалим из пар, а тот который подошёл назначим текущим,
          // таким оброзом мы найдём непрерывающуюся цепочку из аминокислот
          if (mass2aa(d1) != '*') { result += mass2aa(d1); _curr = pair; f = false }
          if (mass2aa(d2) != '*') { result += mass2aa(d2); _curr = pair; f = false }

        }
      }
      // удаляем текущих кусок
      pairs -= curr

    }
    println(result.mkString)
    println("TVVWTGQERAHNREGGDQRVTKCKRFYCKYVADEHLYAGYQTQLCFSERRRNHWPPQWANQRYYWWFQHAPPPQHTNLSCETMNNQWLHYSTWNANYP")

  }

  def round(m: Double): Int = (m * 100000).toInt / 1000

  def diff(m1: Double, m2: Double) = math.abs(round(m1 - m2))


}
