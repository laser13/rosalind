package ru.pavlenov.scala.homework.rosalind.graphs

import ru.pavlenov.scala.libs.easygraph.{Algor, DiGraph, UnGraph}
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 20
 * Какой сам? by Pavlenov Semen 17.07.14.
 * Breadth-First Search
 * http://rosalind.info/problems/bfs/
 *
 * Given:
 * A simple directed graph with n≤10^3^ vertices in the edge list format.
 *
 * Return:
 * An array D[1..n] where D[i] is the length of a shortest path from the vertex 1 to the vertex i (D[1]=0). If i is not reachable from 1 set D[i] to −1.
 */

object Bfs {

  def start() {

    println("Breadth-First Search")
    println("from http://rosalind.info/problems/bfs/")
    println("==========================")

    val data = File.fromData(this).map(_ split "\\s").map(e => (e(0).toInt, e(1).toInt))
    val graph = DiGraph(data)

    val dist = Algor.shortestPathsBellmanFord(graph, 1)
    println(dist.map(d => if (d == Algor.infinitiInt) -1 else d).mkString(" "))

  }

}