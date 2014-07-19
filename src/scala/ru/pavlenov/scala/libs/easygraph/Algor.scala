package ru.pavlenov.scala.libs.easygraph

import scala.collection.immutable
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

  /**
   * Ищем кратчайшие расстояния от указанного узла до остальных
   *
   * @param graph
   */
  def findShortestPaths(graph: DiGraph[Int], start: Int) = {

    val dist = new Array[Int](graph.nodes.size)
    for (i <- 0 until dist.length) dist(i) = Int.MaxValue-100
    dist(start-1) = 0

    val nodes = topologicalSort(graph)
    for (node <- nodes) {
      for (e <- node.out) {
        update(e.nodes)
      }
    }

    def update(e: (Int, Int)) {
      dist(e._2-1) = math.min(dist(e._2-1), dist(e._1-1) + 1)
    }
    dist
  }

  /**
   * Ищем самый длинный путь (динамическое программирование)
   *
   * @param graph
   * @tparam V
   * @return
   */
  def findLongestPath[V](graph: DiGraph[V]) = {

    // Считаем очки для всех узлов
    def calcScore(node: DiNode[V]) {

      // Пришла нода, посмотрим кто на неё ссылается, и какие очки были у них
      // Выберем максимальный и запишем для текущей
      var maxScore = Some(Int.MinValue)
      var maxEdge: Option[DiNode[V]] = None
      for (edge <- node.in) {
        val prevNode = graph ? edge._1
        val score1 = prevNode.score._1.getOrElse(0) + 1
        if (maxScore.get < score1) { maxScore = Some(score1); maxEdge = Option(prevNode) }
      }
      node.score = (if (maxScore.getOrElse(0) == Int.MinValue) None else maxScore, maxEdge)
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
        if (score._1.get > maxScore) {
          maxScore = score._1.get
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
            val n = graph ? edge._2
            if (!dfs(n)) return false
          }
          node.color = Color.BLACK
          resultSort += node
          true
      }
    }

    if (!dfs(graph.findRoots().headOption.get._2)) throw new Error("This cycle graph")

    resultSort.reverse

  }

  def isCycle[V](graph: DiGraph[V], node: DiNode[V]): Boolean = {

    val start = node
    var cycle = false

    next(node)

    def next(node: DiNode[V]): Unit = {
      for (edge <- node.out) {
        val n = graph ? edge._2
        if (start.value == n.value) cycle = true
        else next(n)
      }
    }

    cycle

  }

  /**
   * Алгоритм Беллмана–Форда — алгоритм поиска кратчайшего пути во взвешенном графе.
   * За время O(|V| × |E|) алгоритм находит кратчайшие пути от одной вершины графа до всех остальных.
   * В отличие от алгоритма Дейкстры, алгоритм Беллмана–Форда допускает рёбра с отрицательным весом.
   * Предложен независимо Ричардом Беллманом и Лестером Фордом.
   * http://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%91%D0%B5%D0%BB%D0%BB%D0%BC%D0%B0%D0%BD%D0%B0_%E2%80%94_%D0%A4%D0%BE%D1%80%D0%B4%D0%B0
   *
   * @param graph
   * @param start
   */
  def shortestPathsBellmanFord(graph: DiGraph[Int], start: Int) = {

    def |(i: Int) = i-1

    val nodes = graph.nodes

    val dist, track = new Array[Int](graph.nodes.size)
    for (i <- 0 until dist.length) dist(i) = Int.MaxValue/2
    dist(|(start)) = 0

    for (i <- 0 until nodes.size; e <- graph.edges) {
      update(e.nodes, 1)
    }

    def update(e: (Int, Int), w: Int) {
      dist(|(e._2)) = math.min(dist(|(e._2)), dist(|(e._1)) + w)
    }
    dist

  }

  /**
   * Алгори́тм Де́йкстры (англ. Dijkstra’s algorithm) — алгоритм на графах, изобретённый
   * нидерландским ученым Э. Дейкстрой в 1959 году. Находит кратчайшее расстояние от одной из вершин графа
   * до всех остальных. Алгоритм работает только для графов без рёбер отрицательного веса.
   *
   * http://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%94%D0%B5%D0%B9%D0%BA%D1%81%D1%82%D1%80%D1%8B
   *
   * @param graph
   * @param start
   */
  def shortestPathsDijkstra(graph: DiGraph[Int], start: Int) = {

  }

}
