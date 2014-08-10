package ru.pavlenov.scala.homework.rosalind.algorithms.graph

import ru.pavlenov.scala.utils.File
import ru.pavlenov.scala.libs.easygraph

/**
 * ⓭ + 02
 * Какой сам? by Pavlenov Semen 02.08.14.
 * Encoding Suffix Trees 
 * http://rosalind.info/problems/Suff/
 *
 * Given:
 * A DNA string s of length at most 1kbp.
 *
 * Return:
 * The substrings of s∗ encoding the edges of the suffix tree for s. You may list these substrings in any order.
 */

object Suff {

  def start() {

    println("Encoding Suffix Trees ")
    println("from http://rosalind.info/problems/Suff/")
    println("==========================")

    val data = File.fromData(this)(0)

    val trie = new easygraph.Trie()

    for (i <- 0 until data.length) trie.insertString(data.substring(i))

    trie.printTrieSub()

  }

}