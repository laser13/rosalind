package ru.pavlenov.scala.libs.easygraph

/**
 * ⓭ + 32
 * Какой сам? by Pavlenov Semen 12.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */

import scala.collection.immutable.Set

class DiNode extends Node {

  var in: Set[Edge] = Set[Edge]()
  var out: Set[Edge] = Set[Edge]()

}

trait Node {

  var edges: Set[Edge] = Set[Edge]()

}
