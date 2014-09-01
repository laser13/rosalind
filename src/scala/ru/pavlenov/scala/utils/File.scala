package ru.pavlenov.scala.utils

import scala.io.Source
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets

/**
 * ⓭ + 01
 * Какой сам? by Pavlenov Semen 05.07.14.
 */
object File {

  def getClassDir(clazz: AnyRef) = System.getProperty("user.dir") + "/src/scala/" + clazz.getClass.getName.replaceAll("\\.", "/").replace("$", "")

  def fromData(implicit clazz: AnyRef) = Source.fromFile(getClassDir(clazz) + ".data").getLines().filterNot(_.isEmpty).toArray

  def fromFile(implicit clazz: AnyRef, fileName: String) = Source.fromFile(getClassDir(clazz) + "." + fileName).getLines().filterNot(_.isEmpty).toArray

  def readFasta(clazz: AnyRef) = {
    val header = """>(.+)(\|.+)?""".r
    var lines = Source.fromFile(getClassDir(clazz) + ".data").getLines().filterNot(_.isEmpty)
    var sequences = List[(String, String)]()
    while (lines.nonEmpty) {
      val header(name, annotation) = lines.next()
      val (seq, rest) = lines.span(_(0) != '>')
      sequences = (name, seq.mkString) :: sequences
      lines = rest
    }
    sequences.reverse
  }


  def toFile(implicit clazz: AnyRef, fileName :String, data: String) = {
    Files.write(Paths.get(getClassDir(clazz) + "." + fileName), data.getBytes(StandardCharsets.UTF_8))
  }

  def getFile(implicit clazz: AnyRef, fileName: String): java.io.File = new java.io.File(getClassDir(clazz) + "." + fileName)

}
