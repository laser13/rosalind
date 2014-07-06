package ru.pavlenov.scala.utils

import scala.io.Source

/**
 * ⓭ + 01
 * Какой сам? by Pavlenov Semen 05.07.14.
 */
object File {

  def getClassDir(className: String) = {
    System.getProperty("user.dir") + "/src/scala/" + className.replaceAll("\\.", "/").replace("$", "")
  }

  def fromData(className: String) = {
    Source.fromFile(getClassDir(className) + ".data").mkString
  }

  def readFasta(className: String) = {
    val header = """>(.+)(\|.+)?""".r
    var lines = Source.fromFile(getClassDir(className) + ".data").getLines().filterNot(_.isEmpty)
    var sequences = List[(String, String)]()
    while (lines.nonEmpty) {
      val header(name, annotation) = lines.next()
      val (seq, rest) = lines.span(_(0) != '>')
      sequences = (name, seq.mkString) :: sequences
      lines = rest
    }
    sequences.reverse
  }

}
