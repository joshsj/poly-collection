package poly.collection.search

import poly.collection._
import poly.collection.mut._
import poly.collection.node._

/**
 * An enumerator that executes a search on trees (assumes that there's no loop).
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @since 0.1.0
 */
class TreeSearchIterator[S](
  val start: S,
  val fringe: Queue[SearchNode[S]]
)(implicit ss: StateSpace[S]) extends SearchIterator[S] {

  private[this] var curr: SearchNode[S] = SearchNode.dummy

  fringe += SearchNode(start, 0, curr)

  def currentNode = curr

  def advance() = {
    if (fringe.notEmpty) {
      curr = fringe.pop()
      fringe ++= ss.succ(curr.data).map(s => SearchNode(s, curr.depth + 1, curr))
      true
    }
    else false
  }


}

class DepthFirstTreeSearchIterator[S](start: S)(implicit ss: StateSpace[S])
  extends TreeSearchIterator[S](start, ArrayStack[SearchNode[S]]())(ss)

class BreadthFirstTreeSearchIterator[S](start: S)(implicit ss: StateSpace[S])
  extends TreeSearchIterator[S](start, ArrayQueue[SearchNode[S]]())(ss)