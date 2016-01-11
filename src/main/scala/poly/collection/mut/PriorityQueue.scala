package poly.collection.mut

import poly.algebra._
import poly.collection._

/**
 * @author Tongfei Chen
 */
trait PriorityQueue[T] extends Queue[T] {

  /** Returns an iterable collection of the elements in this priority queue.
    * @note The elements are not guaranteed to be sorted by the order imposed on the priority queue.
    */
  def elements: Iterable[T]

  /** Retrieves the order under which the elements are sorted. */
  def order: WeakOrder[T]

}