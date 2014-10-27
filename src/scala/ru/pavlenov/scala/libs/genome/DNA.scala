package ru.pavlenov.scala.libs.genome

/**
 * ⓭ + 52
 * Какой сам? by Pavlenov Semen 31.08.14.
 */
class DNA(text: String) {

  def reverse = DNA.reverse(text)

}

object DNA {

  def apply(text: String) = new DNA(text)

  def reverse(text: String) = {
    var sb = ""
    for (ch <- text) {
      if (ch == 'A') sb += 'T'
      if (ch == 'T') sb += 'A'
      if (ch == 'C') sb += 'G'
      if (ch == 'G') sb += 'C'
    }
    sb.reverse
  }

}
