package ru.pavlenov.scala.libs.easygraph

/**
 * ⓭ + 32
 * Какой сам? by Pavlenov Semen 12.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */

import scala.collection.immutable

class DiNode[V](value: V) extends Node[V](value) {

  var in: Set[DiEdge[V]] = immutable.Set[DiEdge[V]]()
  var out: Set[DiEdge[V]] = immutable.Set[DiEdge[V]]()

  def ~>(that: DiNode[V]) = DiEdge(this.value, that.value)

  def +:(in: DiEdge[V]) = this.in = this.in + in
  def :+(out: DiEdge[V]) = this.out = this.out + out

  def printIn() = if (in.size == 0) "empty" else in.map(e => e._1).mkString(", ")
  def printOut() = if (out.size == 0) "empty" else out.map(e => e._2).mkString(", ")

  def toFullString: String = "["+printIn()+"] > " + value.toString + " > ["+printOut()+"]"

  override def toString: String = value.toString

}

object DiNode {
  def apply[V](v: V) = new DiNode[V](v)
}

class UnNode[V](value: V) extends Node[V](value) {

  def ~(that: UnNode[V]) = UnEdge(this.value, that.value)

  def +(edge: UnEdge[V]) { this.edges = this.edges + edge }

  override def toString: String = "E(" + edges.size + ")"

}

object UnNode {
  def apply[V](v: V) = new UnNode[V](v)
}

sealed case class Node[V](value: V) {
  var edges: Set[Edge[V]] = immutable.Set[Edge[V]]()
}