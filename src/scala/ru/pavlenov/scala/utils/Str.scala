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
  def counts(str: String): Map[Char, Int] = str.groupBy(c => c.toUpper).map(e => (e._1, e._2.length)).withDefaultValue(0)

}
