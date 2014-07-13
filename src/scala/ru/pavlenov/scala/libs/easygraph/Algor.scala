package ru.pavlenov.scala.libs.easygraph

import scala.collection.mutable.ArrayBuffer

/**
 * ⓭ + 57
 * Какой сам? by Pavlenov Semen 13.07.14.
 * ${TITLE}
 * ${URL}
 *
 * ${GIVEN}
 * ${RETURN}
 */
object Algor {

  def findLongestPath[V](graph: DiGraph[V]) = {

    // Считаем очки для всех узлов
    def calcScore(node: DiNode[V]) {

      // Пришла нода, посмотрим кто на неё ссылается, и какие очки были у них
      // Выберем максимальный и запишем для текущей
      var maxScore = 0
      var maxEdge: Option[DiNode[V]] = None
      for (edge <- node.in) {
        val prevNode = graph ? edge._1
        val score1 = prevNode.score._1 + 1
        if (maxScore < score1) { maxScore = score1; maxEdge = Option(prevNode) }
      }
      node.score = (maxScore, maxEdge)
    }

    /**
     * Строит путь основываясь на подсчитанных баллах
     * @param graph
     */
    def track(graph: DiGraph[V]) = {

      // Сюда будем складывать путь
      var path = List[DiNode[V]]()

      // Первым делом нужно найти узел с максимальным счётом
      var startNode: Option[DiNode[V]] = None
      var maxScore = 0
      graph.nodes.toArray.foreach(pair => {
        val score = pair._2.score
        if (score._1 > maxScore) {
          maxScore = score._1
          startNode = Option(pair._2)
        }
      })

      // Теперь просто разворачиваем этот клубок пока не упрёмся
      while (startNode.nonEmpty) {
        path = path :+ startNode.get
        startNode = startNode.get.score._2
      }
      path.reverse
    }

    // Для топологически отсортированного графа считаем очки для каждого узла
    val nodes = topologicalSort(graph)
    for (node <- nodes) calcScore(node)
    track(graph)

  }

  /**
   * Топологическая сортировка
   * http://habrahabr.ru/post/100953/
   *
   * @param graph
   * @tparam V
   * @return
   */
  def topologicalSort[V](graph: DiGraph[V]) = {

    var resultSort = ArrayBuffer[DiNode[V]]()
    def dfs(node: DiNode[V]): Boolean = {
      node.color match {
        case Color.GRAY => false
        case Color.BLACK => true
        case _ =>
          node.color = Color.GRAY
          for (edge <- node.out) {
            if (!dfs(graph ? edge._2)) return false
          }
          node.color = Color.BLACK
          resultSort += node
          true
      }
    }

    if (!dfs(graph.findRoots().headOption.get._2)) throw new Error("This cycle graph")

    resultSort.reverse

  }

}
