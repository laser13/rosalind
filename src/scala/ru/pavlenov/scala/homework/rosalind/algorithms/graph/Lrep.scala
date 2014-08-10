package ru.pavlenov.scala.homework.rosalind.algorithms.graph

import ru.pavlenov.scala.utils.File

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * ⓭ + 17
 * Какой сам? by Pavlenov Semen 02.08.14.
 * Finding the Longest Multiple Repeat 
 * http://rosalind.info/problems/lrep/
 *
 * Given:
 * A DNA string s (of length at most 20 kbp) with $ appended, a positive integer k, and a list of edges defining the suffix tree of s. Each edge is represented by four components:  the label of its parent node in T(s); the label of its child node in T(s); the location of the substring t of s∗ assigned to the edge; and the length of t.
 *
 * Return:
 * The longest substring of s that occurs at least k times in s. (If multiple solutions exist, you may return any single solution.)
 */

object Lrep {

  def start() {

    println("Finding the Longest Multiple Repeat ")
    println("from http://rosalind.info/problems/lrep/")
    println("==========================")

    var data = File.fromData(this)
    val str = data.head; data = data.tail
    val k = data.head.toInt; data= data.tail

    println(str.length)
    println(k)

    var maxdep = 0
    var maxnode = ""

    val trie = new Trie()
    val edges: Array[trie.Edge] = data.map(_.split(" ")).map(el => trie.Edge(el(0).substring(4).toInt, el(1).substring(4).toInt, el(2).toInt, el(3).toInt))

    for (i <- 0 until edges.size) trie.addEdge(edges(i))

    trie.recalc()

    val node = trie.nodes.filter(e => e._2.count >= k && e._2.parent != 0).maxBy(e => e._2.depth)._2

    println(node)

    var sub = ""
    def path(curr: trie.Node) {
      if (curr.parent > 0) {
        sub = str.substring(curr.idx-1, curr.idx + curr.len - 1) + sub
        path(trie.nodes(curr.parent))
      }
    }

    path(node)

    println(sub)

  }

}

class Trie {

  val nodes = mutable.Map[Int, Node](1 -> Node())

  case class Edge(from: Int, to: Int, index: Int, len: Int)
  case class Node(var count: Int = 0,
                  depth: Int = 0,
                  idx: Int = 0,
                  len: Int = 0,
                  parent: Int = 0,
                  children: ArrayBuffer[Int] = ArrayBuffer[Int]()) {
    override def toString = s"cnt=$count	dep=$depth	par=$parent	chil=$children"
  }

  def addEdge(edge: Edge) {
    if (!nodes.contains(edge.to)) nodes += (edge.to -> Node(parent = edge.from, len = edge.len, depth = nodes(edge.from).depth + edge.len, idx = edge.index))
    nodes(edge.from).children += edge.to
  }

  def recalc() {

    go(1)

    def go(i: Int): Int = {

      var res: Int = 0
      val curr = nodes(i)
      if (i != 1 && curr.children.size == 0) res = 1
      else {
        for (child <- curr.children) {
          res += go(child)
        }
      }
      curr.count = res
      res
    }

  }

}