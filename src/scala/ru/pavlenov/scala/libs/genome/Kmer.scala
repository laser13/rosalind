package ru.pavlenov.scala.libs.genome

/**
 * ⓭ + 58
 * Какой сам? by Pavlenov Semen 24.10.14.
 */
object Kmer {

  /**
   * Расстояние Хэмминга — число позиций, в которых соответствующие символы двух слов одинаковой длины различны
   * https://ru.wikipedia.org/wiki/%D0%A0%D0%B0%D1%81%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5_%D0%A5%D1%8D%D0%BC%D0%BC%D0%B8%D0%BD%D0%B3%D0%B0
   *
   * @param text1
   * @param text2
   * @return
   */
  def hammingDistance(text1: String, text2: String) = (text1 zip text2) count (x => x._1 != x._2)

  def countN(text: String, pattern: String, maxMismatch: Int) = {

    var count = 0
    val textLen = text.length
    val patternLen = pattern.length
    val diff = textLen - patternLen

    def find(kmer: String): Boolean = {
      var mismatch = 0
      for (j <- 0 until patternLen) {
        if (kmer(j) != pattern(j)) mismatch += 1
        if (mismatch > maxMismatch) return false
      }
      true
    }
    for (i <- 0 to diff) if (find(text.substring(i, patternLen + i))) count += 1
    count
  }

/*
  ImmediateNeighbors(Pattern)
        Neighborhood ← set consisting of single string Pattern
        for i = 1 to |Pattern|
            symbol ← i-th nucleotide of Pattern
            for each nucleotide x different from symbol
                Neighbor ← Pattern with the i-th nucleotide substituted by x
                add Neighbor to Neighborhood
        return Neighborhood

*/

  def ImmediateNeighbors(pattern: String) = {

  }


/*
Neighbors(Pattern, d)
        if d = 0
            return {Pattern}
        if |Pattern| = 1
            return {A, C, G, T}
        Neighborhood ← an empty set
        SuffixNeighbors ← Neighbors(Suffix(Pattern), d)
        for each string Text from SuffixNeighbors
            if HammingDistance(Suffix(Pattern), Text) < d
                for each nucleotide x
                    add x • Text to Neighborhood
            else
                add FirstSymbol(Pattern) • Text to Neighborhood
        return Neighborhood
*/

  /**
   *
   * https://stepic.org/lesson/CS-Generating-the-Neighborhood-of-a-String-3014/step/1?course=Bioinformatics-Algorithms&unit=1008
   *
   * @param pattern
   * @param d
   * @return
   */
  def neighbors(pattern: String, d: Int): List[String] = {
    if (d == 0) return List(pattern)
    if (pattern.length == 1) return List("A", "T", "G", "C")
    val suffPattern = pattern.substring(1)
    val firstSymbol = pattern.head
    var neighborhood = List[String]()
    val suffixNeighbors = neighbors(suffPattern, d)

    for (text <- suffixNeighbors) {
      if (hammingDistance(suffPattern, text) < d) {
        for (nucleotide <- nucleotides) {
          neighborhood :+= nucleotide + text
        }
      } else {
        neighborhood :+= firstSymbol + text
      }
    }
    neighborhood
  }

  val nucleotides = Set('A', 'T', 'G', 'C')

}
