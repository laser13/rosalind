package ru.pavlenov.scala.libs.algorithm.string

/**
 * ⓭ + 07
 * Какой сам? by Pavlenov Semen 27.07.14.
 */
object Common {

  /**
   * Все варианты перемешивания 2 строк с сохранением порядка
   * @param s строка номер раз
   * @param t строка номер два
   * @return
   */
  def mix(s: String, t: String): List[String] = (s,t) match {
    case ("", `t`) => List(t)
    case (`s`,"") => List(s)
    case (`s`, `t`)  =>
      mix(s.substring(1),t).map(s(0) + _) ++ mix(s,t.substring(1)).map(t(0) + _)
  }

}
