package ru.pavlenov.scala.homework.rosalind.probability

import ru.pavlenov.scala.utils.File

/**
 * ⓭ + 15
 * Какой сам? by Pavlenov Semen 09.08.14.
 * Counting Disease Carriers 
 * http://rosalind.info/problems/Afrq/
 *
 * Given:
 * An array A for which A[k] represents the proportion of homozygous recessive individuals for the k-th Mendelian factor in a diploid population. Assume that the population is in genetic equilibrium for all factors.
 *
 * Return:
 * An array B having the same length as A in which B[k] represents the probability that a randomly selected individual carries at least one copy of the recessive allele for the k-th factor.
 */

object Afrq {

  def start() {

    println("Counting Disease Carriers ")
    println("from http://rosalind.info/problems/Afrq/")
    println("==========================")

    // https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%BA%D0%BE%D0%BD_%D0%A5%D0%B0%D1%80%D0%B4%D0%B8_%E2%80%94_%D0%92%D0%B0%D0%B9%D0%BD%D0%B1%D0%B5%D1%80%D0%B3%D0%B0

    /*
    Согласно принципу Харди-Вайнберга p^2 + 2*p*q + q^2 = 1
    Где  p^2  — доля гомозигот по одному из аллелей;  p  — частота этого аллеля;
    q^2  — доля гомозигот по альтернативному аллелю;  q  — частота соответствующего аллеля;
    2pq  — доля гетерозигот

    Нам дана доля гомозигот по аллелю q (т.е. q^2) нужно найти долю гетеразигот + гомозигот, т.е. q^2 + 2qp

    Решаем уравнение относительно p
    => D = (2q)^2 - 4(q^2 - 1) = 4q^2 - 4q^2 + 4 = 4
    p(1,2) = (-2q +/- 2) / 2 = 1 +/- q => p = 1 - q

    отсяда следует что q^2 + 2qp = 1 - p^2 = 1 - (1-q)^2 = 2q - q^2

    так как нам дано q^2, то что-бы найти q нужно извлеч корень

     */

    val data = File.fromData(this)(0).split(" ").map(_ toFloat).map(math.sqrt(_)).map(p => 2*p - math.pow(p, 2))

    println(data.map("%.3f".format(_).replace(",", ".")).mkString(" "))

  }

}