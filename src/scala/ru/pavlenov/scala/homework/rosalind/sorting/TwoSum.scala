package ru.pavlenov.scala.homework.rosalind.sorting

import ru.pavlenov.scala.utils.File

object TwoSum {

  def start() {

    val t1 = "2SUM"
    val t2 = "from http://rosalind.info/problems/2sum/"
    println(t1 + "\n" + t2)
    println("=" * t2.length)

    val data = File.fromData(this)
    val matrix = data.tail

    var res = ""
    for (row <- matrix) {
      val a = row.split(" ").map(_ toInt)
      var s = "-1"
      for (i <- 0 until a.length; j <- 0 until a.length if i < j && a(i) == -a(j)) s = (i+1) + " " + (j+1)
      res += s + "\n"
    }
    File.toFile(this, "answer", res)

  }
}