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

class DiLEdge[L](from: Node, to: Node) extends DiEdge(from, to) {

  var label: L = _

  def this(from: Node, to: Node, label: L) {
    this(from, to)
    this.label = label
  }

}

class DiEdge(override val from: Node, override val to: Node) extends Edge {

}

trait Edge {

  val from: Node
  val to: Node

}

class Color extends Enumeration {
  type Color = Value
  val BLACK, WHITE, GRAY, RED, GREEN = Value
}
