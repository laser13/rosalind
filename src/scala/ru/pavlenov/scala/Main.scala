package ru.pavlenov.scala

import ru.pavlenov.scala.homework.rosalind.combinatorics._
import ru.pavlenov.scala.homework.rosalind.graphs._
import ru.pavlenov.scala.homework.rosalind.spectrometry._
import ru.pavlenov.scala.homework.rosalind.string_algorithms._
import ru.pavlenov.scala.utils._

/**
 * ⓭ + 13
 * Какой сам? by Pavlenov Semen 04.07.14.
 */
object Main extends App {
  println("Running scala...\n\n")
  Base.timed { Itwv.start() }
}

object ForFun {

  def curring1(v1: Int)(v2: Int) = {
    v1 * v2
  }

  def curring2(v1: Int)(f2:Int => Int) = {
    val r = f2(v1)
    println(r)
    r
  }

  def curring3[T](v1: T)(f2:T => T) = {
    val r = f2(v1)
    println(r)
    r
  }

  def curring4(v1:Int)(f2:Int => Int)(f3:Int => Int) = {
    val r3 = f3(f2(v1))
    println(r3)
    r3
  }

  def curring[T](f1:Int => T)(f2:T => String) {
    val r1 = f1(6)
    println(f2(r1))
  }

}
