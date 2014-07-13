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

class DiNode[V](val value: V) extends Node[V] {

  var color = Color.WHITE
  var score: (Int, Option[DiNode[V]]) = (0, None)
  var in: Set[DiEdge[V]] = immutable.Set[DiEdge[V]]()
  var out: Set[DiEdge[V]] = immutable.Set[DiEdge[V]]()

  def ~>(that: DiNode[V]) = DiEdge(this.value, that.value)

  def +:(in: DiEdge[V]) = { this.in = this.in + in; this.edges = this.edges + in }
  def :+(out: DiEdge[V]) = { this.out = this.out + out; this.edges = this.edges + out }

  def printIn() = if (in.size == 0) "empty" else in.map(e => e._1).mkString(", ")
  def printOut() = if (out.size == 0) "empty" else out.map(e => e._2).mkString(", ")

  def toFullString: String = "["+printIn()+"] > " + value.toString + " > ["+printOut()+"]"

  def toScoreString: String = value.toString + "->" + score

  override def toString: String = value.toString

}

object DiNode {

  def apply[V](v: V) = new DiNode[V](v)

}

trait Node[V] {

  val value: V
  var edges: Set[Edge[V]] = Set[Edge[V]]()

  override def equals(other: Any) = other match {
    case that: Node[V] => this.value == that.value
    case _ => false
  }

}

object Node {}
