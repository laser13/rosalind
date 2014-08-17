package ru.pavlenov.scala.homework.rosalind.phylogeny

import ru.pavlenov.scala.libs.easygraph.{TreeNode, NewickParser, Tree}
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 31
 * Какой сам? by Pavlenov Semen 17.08.14.
 * Newick Format with Edge Weights 
 * http://rosalind.info/problems/Nkew/
 *
 * Given:
 * A collection of n weighted trees (n≤40) in Newick format, with each tree containing at most 200 nodes; each tree Tk is followed by a pair of nodes xk and yk in Tk.
 *
 * Return:
 * A collection of n numbers, for which the kth number represents the distance between xk and yk in Tk.
 */

object Nkew {

  def start() {

    println("Newick Format with Edge Weights ")
    println("from http://rosalind.info/problems/Nkew/")
    println("==========================")

    val data = File.fromData(this)

    data.sliding(2, 2).toList.map(el => {
      val tree = Tree(el.head, new NewickParser(TreeNode.apply))
      val species = el(1).split(" ")
      print(tree.distanse(species(0), species(1)).toInt + " ")
    })
    println()

  }

}