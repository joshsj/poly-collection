package poly.collection.mut

import poly.collection._

/**
 * Represents a mutable queue/stack/priority queue.
 * @author Tongfei Chen
 * @since 0.1.0
 */
trait Queue[T] {

  /** Returns an iterable list of all the elements in this queue. */
  def elements: Iterable[T]

  /** Checks if this queue is empty. */
  def isEmpty: Boolean = size == 0

  /** Checks if this queue is not empty. */
  def notEmpty: Boolean = size != 0

  /** Pushes the specified element into this queue. */
  def push(x: T): Unit

  /** Returns the top element of the queue. */
  def top: T

  /** Removes the top element from the queue and returns it. */
  def pop(): T

  /** Returns the number of elements in this queue. */
  def size: Int = elements.size

  /** Pushes the specified element into this queue. */
  final def +=(x: T): Unit = push(x)

  /** Pushes all the specified elements into this queue. */
  def pushAll(xs: Traversable[T]) = xs foreach +=

  /** Pushes all the specified elements into this queue. */
  final def ++=(xs: Traversable[T]) = pushAll(xs)

  final def enqueue(x: T) = push(x)
  final def dequeue() = pop()
  final def front = top

  override def toString = "[" + elements.buildString(", ") + "]"

}
