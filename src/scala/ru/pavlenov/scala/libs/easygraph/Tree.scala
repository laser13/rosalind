package ru.pavlenov.scala.libs.easygraph

import scala.collection.mutable
import scala.util.parsing.combinator._

/**
 * ⓭ + 57
 * Какой сам? by Pavlenov Semen 17.08.14.
 */
class Tree(val root: TreeNode) {

  def this(data: String, parser: NewickParser[TreeNode]) {
    this(parser.read(data))
    setParents(root, None)
  }

  def setParents(node: TreeNode, parent: Option[TreeNode]) {
    node.parent = parent
    node.children match {
      case Some(list) => list.map(child => setParents(child, Some(node)))
      case _ =>
    }
  }

  def getLeaves(node: TreeNode = root): List[String] = {
    var list = List[String]()
    deep(node, (node: TreeNode) => {
      if (node.name != "*") list = list :+ node.name
    }); list
  }

  def deep(node: TreeNode, action: (TreeNode) => Unit) {
    action(node)
    node.children match {
      case Some(list) => list.map(child => deep(child, action))
      case _ =>
    }
  }

  def distanse(species1: String, species2: String): Float = {
    var node1: TreeNode = root; var find1 = false
    var node2: TreeNode = root; var find2 = false

    def deep(node: TreeNode) {

      if (node.name == species1) { node1 = node; find1 = true }
      if (node.name == species2) { node2 = node; find2 = true }

      if (!find1 || !find2) {
        node.children match {
          case Some(list) => list.map(child => deep(child))
          case _ =>
        }
      }
    }
    deep(root)

    val path1 = mutable.Set[(Int, Float)]()
    val path2 = mutable.Set[(Int, Float)]()

    def path(node: TreeNode, set: mutable.Set[(Int, Float)]) {
      set += Tuple2(node.id, node.weight)
      node.parent match {
        case Some(n) => path(n, set)
        case _ =>
      }
    }

    path(node1, path1)
    path(node2, path2)

//    println(((path1 ++ path2) -- (path2 & path1)).toList.map(e => e._2))

    ((path1 ++ path2) -- (path2 & path1)).toList.map(e => e._2).sum
  }

  def display() = root.display()

}

object Tree {
  def apply(root: TreeNode) = new Tree(root)
  def apply(data: String, parser: NewickParser[TreeNode]) = new Tree(data, parser)
}

case class TreeNode(id: Int, name: String, var parent: Option[TreeNode], children: Option[List[TreeNode]], weight: Float) {

  def display(parent: String = "", level: Int = 0) {
    printf("%s%s%s\n", parent, "-" * level, name)
    children match {
      case Some(list) => list.foreach(_.display(this.name, level+1))
      case _ =>
    }
  }


  override def toString = id+"~"+name+"~"+weight
}

abstract class TreeParser[T] extends JavaTokenParsers {

  def tree:Parser[T]

  def read(text:String):T = parseAll(tree, text) match {
    case Success(result, next) => result
    case failure => throw new Exception(failure.toString)
  }
}

class NewickParser[T](newTreeNode: (Int, String, Option[T], Option[List[T]], Float) => T) extends TreeParser[T] {
  var i = -1
  def id: Int = { i += 1; i }
  def tree = subtree <~ ";"
  def children: Parser[List[T]] = "(" ~> repsep(subtree, ",") <~ ")"
  def subtree = children~name~weight ^^ { case c~n~w => newTreeNode(id, n, None, Some(c), w) } | leaf
  def leaf = name~weight ^^ { case n~w => newTreeNode(id, n, None, None, w) }
  def name = opt(quoted | unquoted) ^^ { _.getOrElse("*") }
  def unquoted = ident
  def quoted = """'([^']|'')*'""".r  ^^ { _.drop(1).dropRight(1).replace("''","'") }
  def weight = opt(":" ~> floatingPointNumber) ^^ { _.getOrElse("1").toFloat }
}
