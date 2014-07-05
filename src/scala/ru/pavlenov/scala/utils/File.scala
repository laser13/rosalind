package ru.pavlenov.scala.utils

import scala.io.Source

/**
 * ⓭ + 01
 * Какой сам? by Pavlenov Semen 05.07.14.
 */
object File {

  def getClassDir(className: String) = {
    System.getProperty("user.dir") + "/src/scala/" +  className.replaceAll("\\.", "/").replace("$", "")
  }

  def fromData(className: String) = {
    Source.fromFile(getClassDir(className) + ".data").mkString
  }

}
