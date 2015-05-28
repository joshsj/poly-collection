package poly.collection.factory

import poly.algebra._
import poly.collection._
import poly.collection.conversion._
import scala.language.higherKinds
import scala.reflect._



trait TaggedCollectionFactory[C[_]] {

  /** Returns a new builder of this collection type. */
  implicit def newBuilder[T: ClassTag]: CollectionBuilder[T, C]

  /** Creates an empty collection. */
  def empty[T: ClassTag]: C[T] = newBuilder[T].result

  /** Creates a collection by adding the arguments into it. */
  def apply[T: ClassTag](xs: T*): C[T] = {
    val b = newBuilder[T]
    b ++= xs
    b.result
  }

  /** Creates a collection by adding all the elements in the specific traversable sequence. */
  def from[T: ClassTag](xs: Traversable[T]): C[T] = {
    val b = newBuilder[T]
    b ++= xs
    b.result
  }

  implicit def factory: TaggedCollectionFactory[C] = this

}






