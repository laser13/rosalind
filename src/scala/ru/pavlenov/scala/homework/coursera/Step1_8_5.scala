package ru.pavlenov.scala.homework.coursera

/**
 * ⓭ + 10
 * Какой сам? by Pavlenov Semen 19.10.14.
 */
object Step1_8_5 {

  def start(): Unit = {

    val text = "GCGGTTCTAAGCTAGTAATTTAACCACGGGGGCCATTTGGTATTTCCAAGCGTGCTACCCCCTGAAGATCCACCCAGGTTCAAAGAGGCGACGCTGGCGAATCTCCATGAAGAGAGGAATCCGACTTCAAGAGGTTGGACTACGTCCAGTGCTTTTTTATAATGGTGAAACTGACATGGTAATAGCCTAGGCATACTGTGTTCCCCTCAACTTTATCCCTCGTTTGTGCTCTGTCGCCTTATCGAGATTTGTTACGTAGCATTGTACATGTTTGGAGGTGTCGCATTCGGTACAGCTTAATGGGGGCGAAACAGAGCTGGCATTGGTCAATACTGCAATAATTGTGCGACGTCTATGCGTGTTCAAAGTT"
    val pattern = "TGTTCCC"

    println(countN(text, pattern, 3))

  }

  def countN(text: String, pattern: String, maxMismatch: Int) = {

    var count = 0
    val textLen = text.length
    val patternLen = pattern.length
    val diff = textLen - patternLen

    def find(kmer: String): Boolean = {
      var mismatch = 0
      for (j <- 0 until patternLen) {
        if (kmer(j) != pattern(j)) {
          mismatch += 1
        }
        if (mismatch > maxMismatch) {
          return false
        }
      }
      true
    }
    for (i <- 0 to diff) if (find(text.substring(i, patternLen + i))) count += 1
    count
  }

}
