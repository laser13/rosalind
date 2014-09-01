package ru.pavlenov.scala.homework.rosalind.ngs

import ru.pavlenov.scala.utils.File
import scala.sys.process._

/**
 * ⓭ + 42
 * Какой сам? by Pavlenov Semen 24.08.14.
 * Read Filtration by Quality 
 * http://rosalind.info/problems/Filt/
 *
 * Given:
 * A quality threshold value q, percentage of bases p, and set of FASTQ entries.
 *
 * Return:
 * Number of reads in filtered FASTQ entries
 */

object Filt {

  def start() {

    println("Read Filtration by Quality ")
    println("from http://rosalind.info/problems/Filt/")
    println("==========================")

    val data = File.fromData(this)
    val params = data.head.split(" ")

    val q = params(0)
    val p = params(1)

    val list = data.tail
    File.toFile(this, "in.fastq", list.mkString("\n"))


    val cmdline = "/home/laser13/bioinf/fastx_toolkit/bin/fastq_quality_filter -Q33 -q %s -p %s".format(q, p)
    (cmdline #< File.getFile(this, "in.fastq") #> File.getFile(this, "out.fastq")).!

    val res = File.fromFile(this, "out.fastq")

    println(res.length / 4)

  }

}