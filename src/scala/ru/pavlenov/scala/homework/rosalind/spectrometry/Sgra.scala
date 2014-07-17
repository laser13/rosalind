package ru.pavlenov.scala.homework.rosalind.spectrometry

import ru.pavlenov.scala.libs.peptide.AminoAcid
import ru.pavlenov.scala.utils.File
import ru.pavlenov.scala.libs.easygraph._
import ru.pavlenov.scala.libs.easygraph.EdgePredef._

//import scalax.collection.Graph
//import scalax.collection.GraphPredef._
import scalax.collection.edge.Implicits._

/**
 * ⓭ + 13
 * Какой сам? by Pavlenov Semen 09.07.14.
 * Using the Spectrum Graph to Infer Peptides
 * http://rosalind.info/problems/sgra/
 *
 * Given:
 * A list L (of length at most 100) containing positive real numbers.
 *
 * Return:
 * The longest protein string that matches the spectrum graph of L (if multiple solutions exist, you may output any one of them). Consult the monoisotopic mass table.
 */

object Sgra {

  def start() {

    println("Using the Spectrum Graph to Infer Peptides")
    println("from http://rosalind.info/problems/sgra/")
    println("==========================")

    val masses = File.fromData(this).map(_ toDouble)
    val m5a = AminoAcid.mass5aa

    // Создаём граф
    val graph = DiGraph[Double]()

    // Заполняем граф
    for (i <- 0 until masses.length; j <- i until masses.length if i != j) {
      val mi = masses(i)
      val mj = masses(j)
      val diff = AminoAcid.diff(mi, mj)
      m5a(diff) match {
        case c: Char if c != '*' =>
          if (mj < mi) graph <~ (mj ~> mi)
          if (mi < mj) graph <~ (mi ~> mj)
        case _ =>
      }
    }

    // Ищем самый длинный путь в графе
    val path = Algor.findLongestPath(graph)
    // Превращаем его в аминокислоты
    println(AminoAcid.spectro2aa(path.map(_.value)).mkString(""))
  }

}