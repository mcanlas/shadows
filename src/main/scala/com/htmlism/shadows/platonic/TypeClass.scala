package com.htmlism.shadows.platonic

/**
 * @param name
 * @param f
 * @param methods Direct methods only. Nothing inherited.
 * @param supertypes Direct supertypes only. Not the entire hierarchy.
 */
case class TypeClass(name: String, f: UnaryTypeConstructor, methods: List[MethodLike], supertypes: List[TypeClass]) {
  /**
   * Includes methods inherited from supertypes (and their supertypes).
   */
  def allMethods: List[MethodLike] =
    supertypes.flatMap(_.allMethods) ::: methods
}