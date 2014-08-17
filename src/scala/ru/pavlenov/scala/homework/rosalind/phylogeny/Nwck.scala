package ru.pavlenov.scala.homework.rosalind.phylogeny

import ru.pavlenov.scala.libs.easygraph.{TreeNode, NewickParser, Tree}
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


    data.sliding(2, 2).toList.map(el => {
      val tree = Tree(el.head, new NewickParser(TreeNode.apply))
      val species = el(1).split(" ")
//      tree.display()
      print(tree.distanse(species(0), species(1)).toInt + " ")
    })
    println()
    println("54 6 31 13 12 68 5 12 17 17 2 18 11 23 2 13 33 17 7 5 6 2 7 7 2 13 2 22 12 2 2 9 2 16")

  }

}