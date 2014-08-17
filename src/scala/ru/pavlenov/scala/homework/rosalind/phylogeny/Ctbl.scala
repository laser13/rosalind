package ru.pavlenov.scala.homework.rosalind.phylogeny

import ru.pavlenov.scala.libs.easygraph.{TreeNode, NewickParser, Tree}
import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 44
 * Какой сам? by Pavlenov Semen 17.08.14.
 * Creating a Character Table
 * http://rosalind.info/problems/Ctbl/
 *
 * Given:
 * An unrooted binary tree T in Newick format for at most 200 species taxa.
 *
 * Return:
 * A character table having the same splits as the edge splits of T. The columns of the character table should encode the taxa ordered lexicographically; the rows of the character table may be given in any order. Also, for any given character, the particular subset of taxa to which 1s are assigned is arbitrary.
 */

object Ctbl {

  def start() {

    println("Creating a Character Table")
    println("from http://rosalind.info/problems/Ctbl/")
    println("==========================")

    val data = File.fromData(this)
    val tree = Tree(data.head, new NewickParser(TreeNode.apply))

//    tree.display()

    val allLeaves = tree.getLeaves().sorted

    println(allLeaves)

    def printLine(node: TreeNode) = {
      val leaves = tree.getLeaves(node)
      var line = List[Int]()

      for (leave <- allLeaves) {
        if (leaves.contains(leave)) line = line :+ 1
        else line = line :+ 0
      }
      line.mkString(" ")
    }

    tree.deep(tree.root, (node: TreeNode) => {
      if (node.parent != None && node.children != None) {
        println(printLine(node))
      }
    })

  }

}