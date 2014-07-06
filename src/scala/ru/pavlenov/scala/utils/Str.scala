package ru.pavlenov.scala.utils

/**
 * ⓭ + 43
 * Какой сам? by Pavlenov Semen 05.07.14.
 */
object Str {

  /**
   * Подсчитываем кол-во символов в строке (символ => кол-во)
   *
   * @param str
   * @return
   */
  def counts(str: String): Map[Char, Int] = {
    var map: Map[Char, Int] = Map().withDefaultValue(0)
    str.foreach(c => {
      if (!map.contains(c)) {
        map += (c -> str.count(_ == c))
      }
    })
    map
  }

}
