package ru.pavlenov.scala.utils

import scala.collection.mutable
import scala.util.Random
import scala.util.parsing.combinator._

/**
 * ⓭ + 48
 * Какой сам? by Pavlenov Semen 12.08.14.
 */

class NewickParser[T](nf: (String, Double, List[T], Option[T]) => T) extends TreeParser[T] {
  def tree = subtree <~ ";"
  def descendants: Parser[List[T]] = "(" ~> repsep(subtree, ",") <~ ")"
  def subtree = descendants~name~length ^^ { case t~n~l => nf(n, l, t, None) } | leaf
  def leaf = name~length ^^ { case n~l => nf(n, l, Nil, None) }
  def name = opt(quoted | unquoted) ^^ { _.getOrElse("*"+Random.nextString(6)) }
  def unquoted = ident
  def quoted = """'([^']|'')*'""".r  ^^ { _.drop(1).dropRight(1).replace("''","'") }
  def length = opt(":" ~> floatingPointNumber) ^^ { _.getOrElse("0").toDouble }
}

case class Node(name:String, length:Double, descendants:List[Node], var parent: Option[Node]) {
  def display(parent: String = "", level: Int = 0) {
    if (name != "*") printf("%s%s%s\n", parent, "-"*level, name)
    descendants.foreach(_.display(this.name, level+1))
  }

  def distanse(species1: String, species2: String): Int = {
    var node1: Node = this; var find1 = false
    var node2: Node = this; var find2 = false
    def deep(node: Node, parent: Option[Node]) {

      node.parent = parent

      if (node.name == species1) { node1 = node; find1 = true }
      if (node.name == species2) { node2 = node; find2 = true }

      if (!find1 || !find2) {
        for (child <- node.descendants) {
          deep(child, Some(node))
        }
      }
    }
    deep(this, None)

    val path1 = mutable.Set[Node]()
    val path2 = mutable.Set[Node]()

    def path(node: Node, set: mutable.Set[Node]) {
      set += node
      node.parent match {
        case Some(n) => path(n, set)
        case _ =>
      }
    }

    path(node1, path1)
    path(node2, path2)

//    println(path1, path2, (path1 ++ path2) -- (path2 & path1))

    ((path1 ++ path2) -- (path2 & path1)).size
  }

  def find(name: String): Int = {
    var depth = 0
    def deep(node: Node, level: Int = 0) {
      if (node.name == name) depth = level
      for (child <- node.descendants) {
        deep(child, level + 1)
      }
    }
    deep(this)
    depth
  }

}

abstract class TreeParser[T] extends JavaTokenParsers {

  def tree:Parser[T]

  def read(text:String):T = parseAll(tree, text) match {
    case Success(result, next) => result
    case failure => throw new Exception(failure.toString)
  }
}

/*
class Node(key: String = "", nodes: List[Node] = List()) {
    var id: String = key
    var children: List[Node] = nodes

    override def toString(): String =
      "(" + children.map(_.toString()).mkString(",") + ")" + id
  }

  object NewickTreeParser extends JavaTokenParsers {
    val label: Parser[String] = """[a-zA-Z_]*""".r

    val leaf: Parser[Node] = label ^^ {
      case wrd => new Node(key = wrd)
    }

    val subtree = "(" ~ nodeset ~ ")" ~ label ^^ {
      case "(" ~ list ~ ")" ~ word => new Node(key = word, nodes = list)
    }

    def nodeset: Parser[List[Node]] = repsep(node | leaf, ",")

    val node: Parser[Node] = subtree | leaf

    def parse(data: String): Node = this.parse(node, data).get
  }
 */
