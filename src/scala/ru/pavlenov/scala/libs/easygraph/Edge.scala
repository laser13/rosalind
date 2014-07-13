package ru.pavlenov.scala.libs.easygraph

import scala.collection.immutable

/**
 * ⓭ + 33
 * Какой сам? by Pavlenov Semen 12.07.14.
 */

//class DiLEdge[N,L](from: N, to: N) extends DiEdge[N](from, to) {
//  var label: L = _
//  def this(from: N, to: N, label: L) {
//    this(from, to)
//    this.label = label
//  }
//  override def toString: String = "(" + from + "~>" + to + ")"
//}

class DiEdge[V](override val nodes: (V, V)) extends Edge[V] {

  override def _1 = nodes._1
  override def _2 = nodes._2

  override def toString: String = "(" + nodes._1 + "~>" + nodes._2 + ")"

}

object DiEdge {

  def apply[V](f: V, t: V) = new DiEdge[V]((f, t))
  def apply[V](nodes: (V,V)) = new DiEdge[V](nodes)

}

trait Edge[V] {

  val nodes: (V, V)

  def _1 = nodes._1
  def _2 = nodes._2

  override def equals(other: Any) = other match {
    case that: Edge[V] => this._1 == that._1 && this._2 == that._2
    case _ => false
  }

}
