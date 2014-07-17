package ru.pavlenov.scala.homework.rosalind.graphs

import ru.pavlenov.scala.libs.easygraph.UnGraph
import ru.pavlenov.scala.libs.easygraph.EdgePredef._
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 52
 * Какой сам? by Pavlenov Semen 17.07.14.
 * Degree Array 
 * http://rosalind.info/problems/deg/
 *
 * Given:
 * A simple graph with n≤10^^3 vertices in the edge list format.
 *
 * Return:
 * An array D[1..n] where D[i] is the degree of vertex i.
 */

object Deg {

  def start() {

    println("Degree Array ")
    println("from http://rosalind.info/problems/deg/")
    println("==========================")

    val data = File.fromData(this).map(_ split "\\s").map(e => (e(0).toInt, e(1).toInt))
    val graph = UnGraph(data)

//    println(graph.edges)
//    println(graph.nodes)

    for (i <- 1 to graph.nodes.size) print(graph.nodes(i).edges.size + " ")
    println()

  }

}