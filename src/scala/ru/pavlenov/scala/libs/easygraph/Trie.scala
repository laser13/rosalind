package ru.pavlenov.scala.libs.easygraph

import scala.collection.mutable
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
  val root = new TrieNode('*', n)

  class TrieNode(val value: Char, val index: Int, val children: mutable.Map[Char, TrieNode] = mutable.HashMap[Char, TrieNode]()) {
    var leaf = false
    override def toString = index + "|" + children.toString()
  }

  def insertString(str: String) {
    var curr = root
    for (ch <- str) {
      if (!curr.children.contains(ch)) {
        n += 1
        curr.children += (ch -> new TrieNode(ch, n))
      }
      curr = curr.children(ch)
    }
    curr.leaf = true
  }

  def printTrie() = {
    pr(root)

    def pr(curr: TrieNode) {
      for ((ch, node) <- curr.children) {
        println(curr.index + " " + node.index+ " " + ch)
        pr(node)
      }
    }
  }

  override def toString = root.toString

}


