package poly.collection

import poly.algebra.syntax._
import poly.collection.mut._

/**
 * @author Tongfei Chen
 */
object ShuffleTest extends App {


  val b = Seq(1, 2, 3, 4).shuffle.infinitely.take(240000).to(PairWeightedSet.of[Int])

  val bp = 0

}
