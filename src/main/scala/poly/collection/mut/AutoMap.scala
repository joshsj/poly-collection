package poly.collection.mut

import poly.algebra._
import poly.collection.builder._
import poly.collection.factory._

/**
 * Constructs a mutable map given an implicit equivalence relation on the keys.
 * The type of the resulting map is determined from the following fallback relation:
 * <ul>
 *   <li> If the key is endowed with a hashing instance ([[poly.algebra.IntHashing]]),
 *     the result type is [[poly.collection.mut.HashMap]]. Under this condition, the lookup complexity is amortized O(1). </li>
 *   <li> Else, if the key is endowed with a weak order ([[poly.algebra.WeakOrder]]),
 *     the result type is [[poly.collection.mut.RedBlackTreeMap]]. Under this condition, the lookup complexity is O(''n'' log ''n''). </li>
 *   <li> Else, the result type is [[poly.collection.mut.ListMap]]. Under this condition, the lookup complexity is O(''n''). </li>
 * </ul>
 * @since 0.1.0
 * @author Tongfei Chen
 */
object AutoMap extends MapFactory[KeyMutableMap] {
  implicit def newBuilder[K, V](implicit K: Equiv[K]): Builder[(K, V), KeyMutableMap[K, V]] = K match {
    case kh: IntHashing[K] => HashMap.newBuilder[K, V](kh)
    case ko: WeakOrder[K] => RedBlackTreeMap.newBuilder[K, V](ko)
    case ke => ListMap.newBuilder[K, V](ke)
  }
}