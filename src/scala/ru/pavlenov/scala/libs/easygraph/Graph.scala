package ru.pavlenov.scala.libs.easygraph

import scala.collection.immutable

/**
 * ⓭ + 33
 * Какой сам? by Pavlenov Semen 12.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */
class DiGraph[V] {

  type Nodes = Map[V, DiNode[V]]
  type Edges = Set[DiEdge[V]]

  var nodes: Nodes = immutable.Map[V, DiNode[V]]()
  var edges: Edges = immutable.Set[DiEdge[V]]()

  def ?(v: V) = nodes(v)

  def <<<(node: DiNode[V]) {
    nodes += (node.value -> node)
  }

  def <<<(edge: DiEdge[V]) {
    edges = edges + edge
    addToIn(edge)
    addToOut(edge)
  }

  def addToIn(edge: DiEdge[V]) {
    val value = edge._2
    val node = DiNode(value)
    edge +: node
    if (nodes.contains(value)) {
      val oldNode = nodes(value)
      node.in = node.in ++ oldNode.in
      node.out = oldNode.out
    }
    nodes += (value -> node)
  }

  def addToOut(edge: DiEdge[V]) {
    val value = edge._1
    val node = DiNode(value)
    node :+ edge
    if (nodes.contains(value)) {
      val oldNode = nodes(value)
      node.in = oldNode.in
      node.out = node.out ++ oldNode.out
    }
    nodes += (value -> node)
  }

  def findRoots(): Nodes = (for((v, n) <- nodes if n.out.size > 0 && n.in.size == 0  ) yield (v, n)).toMap

}

object DiGraph {

  def apply[E](): DiGraph[E] = new DiGraph[E]()

}

object EdgePredef {

  implicit def any2dinode[V](v: V): DiNode[V] = new DiNode[V](v)

}
