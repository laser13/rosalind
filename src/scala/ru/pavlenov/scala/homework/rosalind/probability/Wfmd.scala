package ru.pavlenov.scala.homework.rosalind.probability

import ru.pavlenov.scala.utils.{Prob, Comb, File}

/**
 * ⓭ + 49
 * Какой сам? by Pavlenov Semen 30.08.14.
 * The Wright-Fisher Model of Genetic Drift
 * http://rosalind.info/problems/Wfmd/
 *
 * Given:
 * Positive integers N (N≤7), m (m≤2N), g (g≤6) and k (k≤2N).
 *
 * Return:
 * The probability that in a population of N diploid individuals initially possessing m copies of a dominant allele, we will observe after g generations at least k copies of a recessive allele. Assume the Wright-Fisher model.
 */

object Wfmd {

  def start() {

    println("The Wright-Fisher Model of Genetic Drift")
    println("from http://rosalind.info/problems/Wfmd/")
    println("==========================")

    // Кол-во диплоидных особей
    val N = 6
    val n = N * 2
    // Кол-во доминантного аллеля
    val m = 8
    // Число поколений
    val g = 5
    // Кол-во рецессивных аллелей через g поколений
    val k = 5

    // Нужно найти вероятность что через g поколений кол-во рецессивного аллеля будет не менее чем k


    /**
     * R solution
N = 5
m = 9
g = 5
k = 6

# A[i,j] = probability that if K generation has i-1 alleles,
# then K+1 generation will have j-1 alleles
A = matrix(0, 2*N+1, 2*N+1)
for (i in 1:(2*N+1)) {
  A[i,] <- dbinom(0:(2*N), 2*N, (i-1)/(2*N))
}

# we start with m alleles
v <- rep(0, 2*N+1)
v[m+1] = 1

# move g generations forward
for (i in 1:g)
  v = v %*% A

# just sum the probabilities
sum(rev(v)[-(1:k)])
     */

    /** python solution
    from scipy.misc import comb

N = 4
m = 6
g = 2
k = 1
# Determine the probabiliy of a given of recessive allels in the first generation.
# Use a binomial random variable with the given parameters.
# Note:  We omit the 0th term throughout the problem, as it has no contribution to the desired probability.
#        For future problems, start the ranges at 0 if the 0 term ever becomes necessary.
p_rec = 1.0 - (m/(2.0*N))
p = [comb(2*N, i)*((p_rec)**i)*(1.0-p_rec)**(2*N-i) for i in range(1,2*N+1)]

# Determine the probabiliy of a given of recessive allels in the 2nd to k-th generations.
# Use the total law of probability, along with the probabilities from the previous generation.
# i.e., P(1 Rec) = P(1 Rec | 0 Rec in previous gen) +  P(1 Rec | 1 Rec in previous gen) + ... + P(1 Rec | 2N Rec in previous gen)
# Notice that the conditional probabilities are binomial terms, similar to the first generation calculations.
for gen in range(2,g+1):
    temp_p = []
    for j in range(1,2*N+1):
        temp_term = [comb(2*N, j)*((x/(2.0*N))**j)*(1.0-(x/(2.0*N)))**(2*N-j) for x in range(1,2*N+1)]
        temp_p.append(sum([temp_term[i]*p[i] for i in range(len(temp_term))]))
    p = temp_p

# Now, sum to get the desired probability.  Note: We have k-1 due to omitting the 0th term.
prob = sum(p[k-1:])
print prob
     */

    def prob(x: Int): Double = x / (n * 1.0)
    def probList(p: Double): List[Double] = (for (k <- 1 to n) yield Prob.binom(n, k, p)).toList

    val p0 = 1.0 - prob(m)
    var p = probList(p0)

    for (l <- 2 to g) {
      var temp_p = List[Double]()
      for (j <- 1 to n) {
        val temp_term = (for (x <- 1 to n) yield Prob.binom(n, j, prob(x))).toList
        temp_p = temp_p :+ (for (i <- 0 until temp_term.length) yield temp_term(i) * p(i)).sum
      }
      p = temp_p
    }

    println(p)
    println(p.slice(k-1, p.length).sum)

  }

}