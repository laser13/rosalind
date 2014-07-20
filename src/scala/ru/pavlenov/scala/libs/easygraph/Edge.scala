package ru.pavlenov.scala.libs.easygraph

import scala.collection.immutable

/**
 * ⓭ + 33
 * Какой сам? by Pavlenov Semen 12.07.14.
 */

class DiEdge[V](from: V, to: V, weight: Int = 1) extends Edge[V](from, to, weight) {

  def _f = nodes._1
  def _t = nodes._2

  override def toString: String = "(" + nodes._1 + "~>" + nodes._2 + "#" + this._w + ")"

}

object DiEdge {
  def apply[V](n1: V, n2: V, w: Int = 1) = new DiEdge[V](n1, n2, w)
  def apply[V](n: (V,V)) = new DiEdge[V](n._1, n._2)
  def apply[V](n: (V,V, Int)) = new DiEdge[V](n._1, n._2, n._3)
}

class UnEdge[V](from: V, to: V, weight: Int = 1) extends Edge[V](from, to, weight) {

  override def toString: String = "(" + nodes._1 + "~" + nodes._2 + ")"

}

object UnEdge {
  def apply[V](n1: V, n2: V, w: Int = 1) = new UnEdge[V](n1, n2, w)
  def apply[V](n: (V,V)) = new UnEdge[V](n._1, n._2)
  def apply[V](n: (V,V, Int)) = new UnEdge[V](n._1, n._2, n._3)
}

sealed case class Edge[V](from: V, to: V, weight: Int = 1) {

  val nodes: (V, V, Int) = (from, to, weight)

  def _1 = nodes._1
  def _2 = nodes._2
  def _w = nodes._3

  override def equals(other: Any) = other match {
    case that: Edge[V] => this._1 == that._1 && this._2 == that._2 && this._w == that._w
    case _ => false
  }

}
