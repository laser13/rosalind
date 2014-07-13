package ru.pavlenov.scala.libs.easygraph

/**
 * ⓭ + 33
 * Какой сам? by Pavlenov Semen 12.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */
class Graph[N,E] {

  var nodes: Set[N] = Set[N]()
  var edges: Set[E] = Set[E]()

}

object Graph {

  def apply[N,E](): Graph[N,E] = new Graph[N,E]()

}
