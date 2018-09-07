package com.htmlism.shadows.plato

/**
  * Implied to be polymorphic given nel type constructors.
  */
// TODO doesn't need to be given plain enum
case class DataClass(name: String, typeParameters: Nel[NullaryTypeConstructor], constructors: Nel[Constructor])

object Constructor {
  def apply(name: String, typeSignatures: TypeSignature*): Constructor =
    Constructor(name, typeSignatures.toList)
}

case class Constructor(name: String, typeSignatures: List[TypeSignature])