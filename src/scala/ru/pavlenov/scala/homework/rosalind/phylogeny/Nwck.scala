package ru.pavlenov.scala.homework.rosalind.phylogeny

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 32
 * Какой сам? by Pavlenov Semen 11.08.14.
 * Distances in Trees 
 * http://rosalind.info/problems/Nwck/
 *
 * Given:
 * A collection of n trees (n≤40) in Newick format, with each tree containing at most 200 nodes; each tree Tk is followed by a pair of nodes xk and yk in Tk.
 *
 * Return:
 * A collection of n positive integers, for which the kth integer represents the distance between xk and yk in Tk.
 */

object Nwck {

  def start() {

    println("Distances in Trees ")
    println("from http://rosalind.info/problems/Nwck/")
    println("==========================")

    val data = File.fromData(this)

  }

}