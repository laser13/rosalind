package ru.pavlenov.scala.utils

import scala.util.parsing.combinator._

/**
 * ⓭ + 48
 * Какой сам? by Pavlenov Semen 12.08.14.
 */

class NewickParser extends JavaTokenParsers {
  def tree                = ident ~ length ~ opt(subtree)
  def subtree:Parser[Any] = "(" ~> repsep(tree, ",") <~ ")"
  def length              = ":" ~> floatingPointNumber
}
