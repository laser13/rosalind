package ru.pavlenov.scala.libs.easygraph

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting
import scalax.collection.immutable.SortedArraySet

/**
 * ⓭ + 14
 * Какой сам? by Pavlenov Semen 22.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */

class Trie {

  var n = 1
  val root = new Node('*', n, 0)

  val nodes = mutable.Map[Int, Node](1 -> root)

  case class Node(value: Char, index: Int, depth: Int, parent: Int = 0, children: mutable.Map[Char, Int] = mutable.HashMap[Char, Int]())

  def insertString(str: String) {
    var curr = root
    for (ch <- str) {
      if (!curr.children.contains(ch)) {
        n += 1
        nodes += (n -> Node(value = ch, index = n, depth = curr.depth+1, parent = curr.index))
        curr.children += (ch -> n)
      }
      curr = nodes(curr.children(ch))
    }
  }

  def printTrie() = {
    pr(root)

    def pr(curr: Node) {
      println(curr.value + "-" + curr.depth + "-" + curr.children.size + " | ")
      for ((ch, index) <- curr.children) {
        pr(nodes(index))
      }
    }
  }

  def printTrieSub() = {

    var list = ArrayBuffer[String]()
    pr("", root)

    println(Sorting.stableSort(list).mkString("\n"))
    
    def pr(s: String, curr: Node) {

      var sub = s
      if (curr.parent > 0) {
        val parent = nodes(curr.parent)
        if (parent.children.size > 1) sub = ""
//        if (curr.value == '$' && parent.value != '*' && sub == "") sub = parent.value+""
      }

      if (curr.value == '$') {
        list += (sub + curr.value)
      }
      if (curr.children.size > 1) {
        if (!hasEnd(curr.children)) list += (sub + curr.value)
      } else {
        sub += curr.value
      }

      if (curr.children.size > 0) {
        for ((ch, index) <- curr.children) {
          pr(sub, nodes(index))
        }
      }
    }

    def hasEnd(children: mutable.Map[Char, Int]): Boolean = {
      children.exists(e => e._1 == '$')
    }

  }

}


