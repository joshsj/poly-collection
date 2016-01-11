package poly.collection.mut

import poly.collection._

/**
 * @author Tongfei Chen
 */
trait MutableSet[T] extends Set[T] {

  def add(x: T)

  def remove(x: T)

  def unionInplace(xs: Traversable[T]) = xs foreach add

  def diffInplace(xs: Traversable[T]) = xs foreach remove

}