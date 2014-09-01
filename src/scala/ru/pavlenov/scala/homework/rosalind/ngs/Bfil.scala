package ru.pavlenov.scala.homework.rosalind.ngs

import ru.pavlenov.scala.utils.File
import scala.sys.process._

/**
 * ⓭ + 47
 * Какой сам? by Pavlenov Semen 30.08.14.
 * Base Filtration by Quality
 * http://rosalind.info/problems/Bfil/
 *
 * Given:
 * FASTQ file, quality cut-off value q, Phred33 quality score assumed.
 *
 * Return:
 * FASTQ file trimmed from the both ends (removed leading and trailing bases with quality lower then q)
 */

object Bfil {

  def start() {

    println("Base Filtration by Quality")
    println("from http://rosalind.info/problems/Bfil/")
    println("==========================")

    val data = File.fromData(this)

    val delta = 33
    val threshold = data.head.toInt
    val list = data.tail

    File.toFile(this, "in.fq", list.mkString("\n"))

    val inFile = File.getClassDir(this) + ".in.fq"
    val outFile = File.getClassDir(this) + ".out.fq"

    //java -jar trimmomatic-0.30.jar PE --phred33 input_forward.fq.gz input_reverse.fq.gz output_forward_paired.fq.gz
    val cmdline = "java -jar /home/laser13/bioinf/Trimmomatic-0.32/trimmomatic-0.32.jar SE -phred33 %s %s LEADING:%s TRAILING:%s".format(inFile, outFile, threshold, threshold)
    cmdline.!

  }

}