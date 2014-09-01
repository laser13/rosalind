package ru.pavlenov.scala.homework.rosalind.genome_assembly

import ru.pavlenov.bio.assemble.Base
import ru.pavlenov.bio.genome.DNA
import ru.pavlenov.scala.libs.easygraph.DeBrGraph
import ru.pavlenov.scala.utils.{Str, File}

import scala.collection.mutable

/**
 * ⓭ + 35
 * Какой сам? by Pavlenov Semen 31.08.14.
 * Constructing a De Bruijn Graph
 * http://rosalind.info/problems/Dbru/
 *
 * Given:
 * A collection of up to 1000 DNA strings of equal length (not exceeding 50 bp) corresponding to a set S of (k+1)-mers.
 *
 * Return:
 * The adjacency list corresponding to the de Bruijn graph corresponding to S∪Src.
 */

object Dbru {

  def start() {

    println("Constructing a De Bruijn Graph")
    println("from http://rosalind.info/problems/Dbru/")
    println("==========================")

    val data = File.fromData(this)
    val rc = data.map(e => DNA.reverse(e))

    val dbGraph = getDeBrGraph(data)
    val dbGraphRc = getDeBrGraph(rc)

    var r = List[(String, String)]()
    dbGraph.foreach(e => {
      e._2.foreach(s => {
        r = r :+ (e._1, s)
      })
    })
    dbGraphRc.foreach(e => {
      e._2.foreach(s => {
        r = r :+ (e._1, s)
      })
    })

    r.sorted.toSet[(String, String)].foreach(e => println("(%s, %s)".format(e._1, e._2)))

  }

  def getDeBrGraph(data: Array[String]) = {
    val result = mutable.Map[String, mutable.Set[String]]()
    for (kmer <- data) {
      val prefix: String = Str.pref(kmer, kmer.length -1)
      val suffix: String = Str.suff(kmer, kmer.length -1)


      if (result.contains(prefix)) {
        result.get(prefix).get += suffix
      }
      else {
        val list = mutable.Set[String]()
        list += suffix
        result += (prefix -> list)
      }
    }
    result
  }

}