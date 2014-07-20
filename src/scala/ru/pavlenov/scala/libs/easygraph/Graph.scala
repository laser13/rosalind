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
class DiGraph[V] extends Graph[V]{

  type Nodes = Map[V, DiNode[V]]
  type Edges = Set[DiEdge[V]]

  var nodes: Nodes = immutable.Map[V, DiNode[V]]()
  var edges: Edges = immutable.Set[DiEdge[V]]()

  def ?(v: V) = nodes(v)

  def <*(node: DiNode[V]) {
    nodes += (node.value -> node)
  }

  def <~(edge: DiEdge[V]) {
    edges = edges + edge
    addToIn(edge)
    addToOut(edge)
  }

  private def addToIn(edge: DiEdge[V]) {
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

  private def addToOut(edge: DiEdge[V]) {
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

  override def toString = "nodes: " + nodes.size + "\n    " + nodes.keys + "\nedges: " + edges.size + "\n   " + edges

}

object DiGraph {
  def apply[E](): DiGraph[E] = new DiGraph[E]()

  def apply(list: Array[(Int, Int)]): DiGraph[Int] = { import EdgePredef._
    val graph = DiGraph[Int]()
    for (i <- 1 to list.head._1) graph <* i
    for (edge <- list.tail) graph <~ (edge._1 ~> edge._2)
    graph
  }

  def apply(list: Array[(Int, Int, Int)]): DiGraph[Int] = { import EdgePredef._
    val graph = DiGraph[Int]()
    for (i <- 1 to list.head._1) graph <* i
    for (edge <- list.tail) graph <~ DiEdge[Int](edge)
    graph
  }

}

/**
 * Undirectional graph
 * @tparam V node value type
 */
class UnGraph[V] extends Graph[V] {

  type Nodes = Map[V, UnNode[V]]
  type Edges = Set[UnEdge[V]]

  var nodes: Nodes = immutable.Map[V, UnNode[V]]()
  var edges: Edges = immutable.Set[UnEdge[V]]()

  /**
   * Find node by value
   * @param v node value
   * @return
   */
  def ?(v: V) = nodes(v)

  /**
   * Add new node
   * @param node new node
   */
  def <*(node: UnNode[V]) = nodes += (node.value -> node)

  /**
   * Add new edge
   * @param edge new edge
   */
  def <~(edge: UnEdge[V]) {
    edges = edges + edge
    addTo(edge)
  }

  private def addTo(edge: UnEdge[V]) {
    val value1 = edge._1
    val node1 = UnNode(value1)
    node1 + edge
    if (nodes.contains(value1)) node1.edges = node1.edges ++ nodes(value1).edges
    nodes += (value1 -> node1)

    val value2 = edge._2
    val node2 = UnNode(value2)
    node2 + edge
    if (nodes.contains(value2)) node2.edges = node2.edges ++ nodes(value2).edges
    nodes += (value2 -> node2)
  }

  override def toString = "UnGraph\nnodes: " + nodes.size + "\n    " + nodes.keys + "\nedges: " + edges.size + "\n   " + edges

}

object UnGraph {
  def apply[E](): UnGraph[E] = new UnGraph[E]()

  def apply(list: Array[(Int, Int)]): UnGraph[Int] = { import EdgePredef._
    val graph = UnGraph[Int]()
    for (i <- 1 to list.head._1) graph <* i
    for (edge <- list.tail) graph <~ (edge._1 ~ edge._2)
    graph
  }

}

sealed class Graph[V] {


}

object EdgePredef {
  implicit def any2dinode[V](v: V): DiNode[V] = new DiNode[V](v)
  implicit def any2unnode[V](v: V): UnNode[V] = new UnNode[V](v)
}
