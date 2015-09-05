package poly.collection

import poly.util.typeclass._
import poly.util.typeclass.ops._
import poly.collection.node._

/**
 * Represents a multi-way tree.
 * @author Tongfei Chen (ctongfei@gmail.com).
 */
trait Tree[+T] {

  import Tree._

  def rootNode: TreeNode[T]

  def root = rootNode.data

  def children = rootNode.children.map(t => ofNode(t))

}

object Tree {

  def ofNode[T](n: => TreeNode[T]): Tree[T] = new Tree[T] {
    def rootNode = n
  }

  /**
   * Formats a tree into an S-expression.
   * @return An S-expression that represents the specific tree.
   */
  implicit def Formatter[T: Formatter]: Formatter[Tree[T]] = new Formatter[Tree[T]] {
    def str(x: Tree[T]): String =
      "(" + x.root.str +
        (if (x.children.size == 0) "" else " ") +
        x.children.buildString(" ")(this) +
      ")"
  }
}
