package ru.pavlenov.scala.homework.rosalind.graphs

import ru.pavlenov.scala.libs.easygraph.{Algor, DiGraph}
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 28
 * Какой сам? by Pavlenov Semen 20.07.14.
 * Bellman-Ford Algorithm
 * http://rosalind.info/problems/bf/
 *
 * Given:
 * A simple directed graph with integer edge weights from −10^3^ to 10^3^ and n≤10^3^ vertices in the edge list format.
 *
 * Return:
 * An array D[1..n] where D[i] is the length of a shortest path from the vertex 1 to the vertex i (D[1]=0). If i is not reachable from 1 set D[i] to x.
 */

object Bf {

  def start() {

    println("Bellman-Ford Algorithm")
    println("from http://rosalind.info/problems/bf/")
    println("==========================")

    val data = File.fromData(this).map(_ split "\\s").map(e => (e(0).toInt, e(1).toInt, if (e.length < 3) 0 else e(2).toInt))
    val graph = DiGraph(data)

    val dist = Algor.shortestPathsBellmanFord(graph, 1)

    println(Algor.infinitiLong)

    println(dist.map(d => if (d > (0.75 * Algor.infinitiLong)) 'x' else d.toString).mkString(" "))

  }

}