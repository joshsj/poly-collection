package poly.collection

import poly.algebra._
import poly.algebra.specgroup._

/**
 * Marker trait for any data structure that contains keys (unique identifiers).
 * @author Tongfei Chen
 * @since 0.1.0
 * @define LAZY The resulting collection is '''lazily''' executed.
 * @define EAGER The resulting collection is '''eagerly''' executed.
 * @define Onlogn Time complexity: O(n log n).
 * @define On Time complexity: O(n).
 * @define Ologn Time complexity: O(log n).
 * @define O1amortized Time complexity: Amortized O(1).
 * @define O1 Time complexity: O(1).
 */
trait Keyed[@sp(Int) K] {

  /** Returns the equivalence relation on keys. */
  def equivOnKey: Equiv[K]

}
