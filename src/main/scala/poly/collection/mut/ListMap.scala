package poly.collection.mut

import poly.algebra._
import poly.algebra.syntax._
import poly.collection._
import poly.collection.evidence._
import poly.collection.exception._
import poly.collection.factory._
import poly.collection.impl.linkedlist._

/**
 * Represents a mutable map backed by a list of key-value pairs.
 * Use only if the number of entries in the map is very small, or no sensible hashing or order of the key type can be found.
 *
 * @since 0.1.0
 * @author Tongfei Chen
 */
class ListMap[K, V] private(private val data: SinglyLinkedList[K, ListMap.Node[K, V]])(implicit override val keyEq: Eq[K]) extends KeyMutableMap[K, V] { self =>

  type Node = ListMap.Node[K, V]

  data.dummy = new Node(default[K], default[V])
  data.dummy.next = data.dummy

  def keySet: Set[K] = new AbstractSet[K] {
    implicit def keyEq = self.keyEq
    def keys = data.entries.map(_.data)
    def contains(x: K) = locateKey(x) ne null
  }

  override def size = data.len

  private[this] def locateKey(x: K): (Node, Node) = {
    var p = data.dummy
    var c = data.dummy.next
    while (c ne data.dummy) {
      if (x === c.data) return (p, c)
      p = c
      c = c.next
    }
    null
  }

  /** @inheritdoc $On */
  override def containsKey(x: K) = locateKey(x) ne null

  /** @inheritdoc $On */
  def ?(x: K) = {
    val pc = locateKey(x)
    if (pc eq null) None
    else {
      val (_, c) = pc
      Some(c.value)
    }
  }

  def add_!(x: K, y: V) = {
    val pc = locateKey(x)
    if (pc eq null) data.prependInplace(new Node(x, y))
    else {
      val (_, c) = pc
      c.value = y
    }
  }

  def clear_!() = data.clear()

  def remove_!(x: K) = {
    val pc = locateKey(x)
    if (pc ne null) {
      data.deleteNodeAfter(pc._1)
    }
  }

  def update(x: K, y: V) = {
    val pc = locateKey(x)
    if (pc eq null) throw new KeyNotFoundException(x)
    val (_, c) = pc
    c.value = y
  }

  def apply(x: K): V = locateKey(x)._2.value

  override def pairs = data.entries.map(n => n.data -> n.value)

  override def keys = data.entries.map(_.data)

  override def values = data.entries.map(_.value)

}

object ListMap extends MapFactory[ListMap, Eq] {

  private[poly] class Node[K, V](var data: K, var value: V) extends SinglyLinkedNodeLike[K, Node[K, V]] {
    var next: Node[K, V] = _
  }

  def newMapBuilder[K: Eq, V]: Builder[(K, V), ListMap[K, V]] = new Builder[(K, V), ListMap[K, V]] {
    private[this] val r = new ListMap[K, V](new SinglyLinkedList[K, Node[K, V]])
    def result = r
    def add(x: (K, V)) = r add_! x
  }

}
