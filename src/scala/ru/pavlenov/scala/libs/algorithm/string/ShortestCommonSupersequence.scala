package ru.pavlenov.scala.libs.algorithm.string

/**
 * ⓭ + 37
 * Какой сам? by Pavlenov Semen 26.07.14.
 *
 * Ищет кратчайшую общую надстроку двух строк
 * http://en.wikipedia.org/wiki/Shortest_common_supersequence
 */
class ShortestCommonSupersequence(strI: String, strJ: String) {

  val lI = strI.length-1
  val lJ = strJ.length-1

  val scoreMatrix = Array.ofDim[Int](lI+1, lJ+1)
  val trackMatrix = Array.ofDim[Char](lI+1, lJ+1)

  var supersequence = ""

  def find(): String = {
    make()
    path(lI, lJ, 's')
    supersequence.reverse
  }

  /**
   * Запускаем динамическое программирование и считаем матрицу весов и трек
   */
  private def make() {

    scoreMatrix(0)(0) = 1
    trackMatrix(0)(0) = '#'
    for (i <- 1 to lI) { scoreMatrix(i)(0) = i+1; trackMatrix(i)(0) = 'i' }
    for (j <- 1 to lJ) { scoreMatrix(0)(j) = j+1; trackMatrix(0)(j) = 'j' }

    for (i <- 1 to lI; j <- 1 to lJ) {
      if (strI(i) == strJ(j)) {
        scoreMatrix(i)(j) = scoreMatrix(i-1)(j-1)+1
        trackMatrix(i)(j) = 'a'
      } else {
        if (scoreMatrix(i-1)(j) < scoreMatrix(i)(j-1)) {
          scoreMatrix(i)(j) = scoreMatrix(i-1)(j)+1
          trackMatrix(i)(j) = 'i'
        } else {
          scoreMatrix(i)(j) = scoreMatrix(i)(j-1)+1
          trackMatrix(i)(j) = 'j'
        }
      }
    }
  }

  /**
   * Идём по матрице трека в обратном направлении
   * @param i координата по i
   * @param j координата по j
   * @param p предыдущий указатель направления
   */
  private def path(i: Int, j: Int, p: Char) {
    val c = trackMatrix(i)(j)
    if (c == '#') {
      supersequence = supersequence + (if (p == 'j') strJ(j) else strI(i))
    } else if (c == 'a') {
      supersequence = supersequence + strI(i)
      path(i-1, j-1, c)
    } else {
      if (c == 'i') {
        supersequence = supersequence + strI(i)
        path(i-1, j, c)
      }
      if (c == 'j') {
        supersequence = supersequence + strJ(j)
        path(i, j-1, c)
      }
    }
  }

}

object ShortestCommonSupersequence {
  def apply(sI: String, sJ: String) = new ShortestCommonSupersequence(sI, sJ)
}
