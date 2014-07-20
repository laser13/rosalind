package ru.pavlenov.scala.homework.rosalind.graphs

import ru.pavlenov.scala.libs.easygraph.{Algor, DiGraph}
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 47
 * Какой сам? by Pavlenov Semen 19.07.14.
 * Dijkstra's Algorithm 
 * http://rosalind.info/problems/dij/
 *
 * Given:
 * A simple directed graph with positive edge weights from 1 to 10^3^ and n≤10^3^ vertices in the edge list format.
 *
 * Return:
 * An array D[1..n] where D[i] is the length of a shortest path from the vertex 1 to the vertex i (D[1]=0). If i is not reachable from 1 set D[i] to −1.
 */

object Dij {

  def start() {

    println("Dijkstra's Algorithm ")
    println("from http://rosalind.info/problems/dij/")
    println("==========================")

    val data = File.fromData(this).map(_ split "\\s").map(e => (e(0).toInt, e(1).toInt, if (e.length < 3) 0 else e(2).toInt))
    val graph = DiGraph(data)

    val res = Algor.shortestPathsDijkstra(graph, 1)

    val dist = res.map(e => e(0))

    println(dist.map(d => if (d == Algor.infinitiInt) -1 else d).mkString(" "))

  }

}