package poly.collection

import poly.algebra.syntax._

/**
 * Trait for an indexed sorted sequence.
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait SortedIndexedSeq[T] extends SortedSeq[T] with IndexedSeq[T] { self =>

  /**
   * Finds the key in a sorted sequence using binary search. The complexity of this operation is O(log n).
   * @param x The key to be found
   * @return Index of key. If not found, None.
   */
  def binarySearch(x: T) = tryBinarySearch(x) match {
    case x if x >= 0 => Some(x)
    case _ => None
  }

  /**
   * Finds the key in a sorted sequence using binary search.
   * If not found, returns the complement (~x) of its lower bound.
   * @param x The key to be found
   * @return Index of key. If not found, complement of the index at which it should be inserted
   */
  def tryBinarySearch(x: T): Int = {
    var l = 0
    var r = length - 1
    while (l <= r) {
      val m = l + (r - l) / 2
      val value = this(m)
      if (x === value) return m
      else {
        if (value < x)
          l = m + 1
        else
          r = m - 1
      }
    }
    ~l
  }

  /**
   * Finds the first element that is greater than the key and returns its index.
   * @param key The key to be found
   * @return The index of the first element that is greater than the key.
   */
  def upperBound(key: T): Int = {
    var len = length
    var first = 0
    while (len > 0) {
      val mid = first + (len / 2)
      if (key < this(mid))
        len /= 2
      else {
        first = mid + 1
        len = len - (len / 2) - 1
      }
    }
    first
  }

  /**
   * Finds the first element that is not less than the key and returns its index.
   * @param key The key to be found
   * @return The index of the first element that is not less than the key.
   */
  def lowerBound(key: T): Int = {
    var len = length
    var first = 0
    while (len > 0) {
      val mid = first + (len / 2)
      if (key > this(mid)) {
        first = mid + 1
        len = len - (len / 2) - 1
      }
      else
        len /= 2
    }
    first
  }

  /** Returns the ''q''-quantile of this sequence under the current sorted order. */
  def quantile(q: Double) = {
    val i = math.floor(self.length * q).toInt
    if (i < self.length)
      if (i < 0) self.head
      else self(i)
    else self.last
  }

}

abstract class AbstractSortedIndexedSeq[T] extends AbstractIndexedSeq[T] with SortedIndexedSeq[T]
