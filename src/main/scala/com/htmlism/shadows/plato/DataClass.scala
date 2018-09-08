package com.htmlism.shadows.plato

/**
  * Implied to be polymorphic given nel type constructors.
  */
case class DataClass(name: String, typeParameters: List[NullaryTypeConstructor], constructors: Nel[Constructor])

object Constructor {
  def apply(name: String, typeSignatures: Parameter*): Constructor =
    Constructor(name, typeSignatures.toList)
}

case class Constructor(name: String, parameters: List[Parameter])

/**
 * @param name Not used in Haskell.
 */
case class Parameter(name: String, sig: TypeSignature)