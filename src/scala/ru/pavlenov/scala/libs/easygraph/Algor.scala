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

  val infinitiInt = (0.9 * Int.MaxValue).toInt
  val infinitiLong = (0.7 * Long.MaxValue).toLong

  /**
   * Ищем кратчайшие расстояния от указанного узла до остальных
   *
   * @param graph
   */
  def findShortestPaths(graph: DiGraph[Int], start: Int) = {

    val dist = new Array[Int](graph.nodes.size)
    for (i <- 0 until dist.length) dist(i) = infinitiInt
    dist(start-1) = 0

    val nodes = topologicalSort(graph)
    for (node <- nodes) {
      for (e <- node.out) {
        setMin(dist, e.nodes)
      }
    }

    dist
  }

  /**
   * Ищем самый длинный путь (динамическое программирование)
   *
   * @param graph
   * @return
   */
  def findLongestPath(graph: DiGraph[Int]) = {

    val scores, path = new Array[Int](graph.nodes.size)
    for (i <- 0 until scores.length) scores(i) = 0

    // Считаем очки для всех узлов
    def calcScore(node: DiNode[Int]) {

      // Пришла нода, посмотрим кто на неё ссылается, и какие очки были у них
      // Выберем максимальный и запишем для текущей
      var maxScore = 0
      var maxNode = node.value
      for (edge <- node.in) {
        val prevNode = graph ? edge._1
        val score = scores(|<(prevNode.value)) + edge._w
        if (maxScore < score) { maxScore = score; maxNode = prevNode.value }
      }
      scores(|<(node.value)) = maxScore
      path(|<(node.value)) = maxNode
    }

    /**
     * Строит путь основываясь на подсчитанных баллах
     */
    def track() = {

      // Сюда будем складывать путь
      var maxPath = List[Int]()

      // Первым делом нужно найти узел с максимальным счётом
      var startNode = 0
      var maxScore = 0
      for (i <- 0 until scores.length) {
        if (scores(i) > maxScore) {
          maxScore = scores(i)
          startNode = |>(i)
        }
      }

      // Теперь просто разворачиваем этот клубок пока не упрёмся
      while (startNode > 0) {
        maxPath = maxPath :+ startNode
        startNode = path(|<(startNode))
      }
      maxPath.reverse
    }

    // Для топологически отсортированного графа считаем очки для каждого узла
    val nodes = topologicalSort(graph)
    for (node <- nodes) calcScore(node)
    track()

  }

  /**
   * Топологическая сортировка
   * http://habrahabr.ru/post/100953/
   *
   * @param graph
   * @return
   */
  def topologicalSort(graph: DiGraph[Int]) = {

    val colors = new Array[Color.Value](graph.nodes.size)
    for (i <- 0 until colors.length) colors(i) = Color.WHITE

    var resultSort = ArrayBuffer[DiNode[Int]]()
    def dfs(node: DiNode[Int]): Boolean = {
      colors(|<(node.value)) match {
        case Color.GRAY => false
        case Color.BLACK => true
        case _ =>
          colors(|<(node.value)) = Color.GRAY
          for (edge <- node.out) {
            val n = graph ? edge._2
            if (!dfs(n)) return false
          }
          colors(|<(node.value)) = Color.BLACK
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

    val nodes = graph.nodes

    println("Nodes: " + graph.nodes.size)
    println("Edges: " + graph.edges.size)

    val dist, track = new Array[Long](graph.nodes.size)
    for (i <- 0 until dist.length) dist(i) = infinitiLong
    dist(|<(start)) = 0

    for (i <- 0 until nodes.size; e <- graph.edges) {
      setMin(dist, e.nodes)
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

    val track = new Array[Int](graph.nodes.size)
    val dist = Array.ofDim[Int](graph.nodes.size, 2)

    for (i <- 0 until dist.length) dist(i)(0) = infinitiInt
    track(|<(start)) = start

    def get(i: Int) = dist(|<(i))(0)
    def set(i: Int, v: Int) = dist(|<(i))(0) = v
    def find(): Int = {
      var min = infinitiInt; var v = 0
      for (i <- 0 until dist.length) if (dist(i)(0) < min && dist(i)(1) == 0) { min = dist(i)(0); v = |>(i) }
      if (min != infinitiInt) dist(|<(v))(1) = 1
      v
    }

    set(start, 0)
    var curr = find()
    while (curr > 0) {
      val node = graph ? curr
//      for (edge <- node.in) {
//        val f = edge._f
//        if (get(f) > get(curr) + edge._w) set(f, get(curr) + edge._w)
//      }

      for (edge <- node.out) {
        val t = edge._t
        if (get(t) > get(curr) + edge._w) set(t, get(curr) + edge._w)
      }
      curr = find()
    }
    dist

  }

  def findSubGraphs(graph: UnGraph[Int]) = {
    var count = 0
    var nodes = graph.nodes
    while (nodes.size > 0) {
      val curr = nodes.headOption.get._2
      count += 1
      walk(curr)
    }

    def walk(node : UnNode[Int]) {
      nodes = nodes - node.value
      for (edge <- node.edges) {
        val v = if (edge._1 == node.value) edge._2 else edge._1
        if (nodes.contains(v)) walk(nodes(v))
      }
    }
    count
  }

  def |<(i: Int) = i-1
  def |>(i: Int) = i+1

  def setMin(dist: Array[Int], e: (Int, Int, Int)) {
    dist(e._2-1) = math.min(dist(e._2-1), dist(e._1-1) + e._3)
  }

  def setMin(dist: Array[Long], e: (Int, Int, Int)) {
    dist(e._2-1) = math.min(dist(e._2-1), dist(e._1-1) + e._3)
  }

}
