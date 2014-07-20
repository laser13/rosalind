package ru.pavlenov.scala.homework.rosalind.graphs

import ru.pavlenov.scala.libs.easygraph.{Algor, UnGraph, DiGraph}
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 32
 * Какой сам? by Pavlenov Semen 20.07.14.
 * Connected Components
 * http://rosalind.info/problems/cc/
 *
 * Given:
 * A simple graph with n≤10^3^ vertices in the edge list format.
 *
 * Return:
 * The number of connected components in the graph.
 */

object Cc {

  def start() {

    println("Connected Components")
    println("from http://rosalind.info/problems/cc/")
    println("==========================")

    val data = File.fromData(this).map(_ split "\\s").map(e => (e(0).toInt, e(1).toInt))
    val graph = UnGraph(data)

//    println(graph)

    val count = Algor.findSubGraphs(graph)

    println(count)

  }

}