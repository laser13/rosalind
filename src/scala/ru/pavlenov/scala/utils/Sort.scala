package ru.pavlenov.scala.utils

/**
 * Created by semen on 02.09.14.
 */
object Sort {

  def insertionSort(a: Array[Int]): Unit = {
    var cnt = 0
    for (i <- 1 until a.length) {
      var k = i
      while (k > 0 && a(k) < a(k-1)) {
        swap(a, k-1, k)
        cnt += 1
        k -= 1
      }
    }

    println(cnt)
  }

  def swap(a: Array[Int], i: Int, j: Int): Unit = {
    val v = a(i)
    a(i) = a(j)
    a(j) = v
  }

  @scala.annotation.tailrec
  def merge[T](ls: List[T], rs: List[T], acc: List[T] = List[T]())(implicit pred: (T, T) => Boolean): List[T] = (ls, rs) match {
    case (Nil, _) => acc ++ rs
    case (_, Nil) => acc ++ ls
    case (l :: ls1, r :: rs1) =>
      if (pred(l, r)) merge(ls1, rs, acc :+ l)
      else merge(ls, rs1, acc :+ r)
  }

  def mergeSort[T](xs: List[T])(implicit pred: (T, T) => Boolean): List[T] = {
    val m = xs.length / 2
    if (m == 0) xs
    else {
      val (l, r) = xs splitAt m
      merge(mergeSort(l), mergeSort(r))
    }
  }

}
