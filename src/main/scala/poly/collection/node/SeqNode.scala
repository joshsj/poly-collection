package poly.collection.node

import poly.collection._
import poly.collection.exception._
import poly.collection.mut._

/**
 * Represents a node that has only one successor node.
 * It is the type of nodes in a typical sequence ([[poly.collection.Seq]]).
 * @since 0.1.0
 */
trait SeqNodeLike[+T, +N <: SeqNodeLike[T, N]] extends ForwardNodeLike[T, N] { self: N =>
  def next: N
  def succ: Iterable[N] = ListSeq(next).filter(_.notDummy)
}

trait SeqNode[+T] extends ForwardNode[T] with SeqNodeLike[T, SeqNode[T]]

object SeqNode {

  object dummy extends SeqNode[Nothing] {
    def isDummy = true
    def data = throw new DummyNodeException
    def next = this
  }

}