package ru.pavlenov.scala.homework.rosalind.algorithms.string

import ru.pavlenov.scala.libs.easygraph.Trie
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 19
 * Какой сам? by Pavlenov Semen 20.07.14.
 * Introduction to Pattern Matching
 * http://rosalind.info/problems/trie/
 *
 * Given:
 * A list of at most 100 DNA strings of length at most 100 bp, none of which is a prefix of another.
 *
 * Return:
 * The adjacency list corresponding to the trie T for these patterns, in the following format.
 * If T has n nodes, first label the root with 1 and then label the remaining nodes with the integers 2 through n in any order you like.
 * Each edge of the adjacency list of T will be encoded by a triple containing the integer representing the edge's
 * parent node, followed by the integer representing the edge's child node, and finally the symbol labeling the edge.
 */

object Trie {

  def start() {

    println("Introduction to Pattern Matching")
    println("from http://rosalind.info/problems/trie/")
    println("==========================")

    val data = File.fromData(this).sorted

    val trie = new Trie()

    for (str <- data) trie.insertString(str)

    trie.printTrie()

  }

}