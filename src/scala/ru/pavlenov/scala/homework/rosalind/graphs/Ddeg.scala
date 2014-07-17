package ru.pavlenov.scala.homework.rosalind.graphs

import ru.pavlenov.scala.libs.easygraph.UnGraph
import ru.pavlenov.scala.libs.easygraph.EdgePredef._
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 45
 * Какой сам? by Pavlenov Semen 17.07.14.
 * Double-Degree Array
 * http://rosalind.info/problems/ddeg/
 *
 * Given:
 * A simple graph with n≤10^^3 vertices in the edge list format.
 *
 * Return:
 * An array D[1..n] where D[i] is the sum of the degrees of i's neighbors.
 */

object Ddeg {

  def start() {

    println("Double-Degree Array")
    println("from http://rosalind.info/problems/ddeg/")
    println("==========================")

    val data = File.fromData(this).map(_ split "\\s").map(e => (e(0).toInt, e(1).toInt))
    val graph = UnGraph(data)

    //    println(graph.edges)
    //    println(graph.nodes)

    for (i <- 1 to graph.nodes.size) {
      val n = graph ? i
      val neighbors = n.edges.map(e => if (e._1 == n.value) e._2 else e._1).toList
      val sum = neighbors.map(v => graph ? v).map(n => n.edges.size).sum
//      println(i, n, neighbors, sum)
      print(sum + " ")
    }
    println()

  }

}