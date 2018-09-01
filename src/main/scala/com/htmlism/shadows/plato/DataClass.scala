package com.htmlism.shadows.plato

case class DataClass(name: String, typeParameters: Nel[NullaryTypeConstructor], constructors: Nel[Constructor])

object Constructor {
  def apply(name: String, typeSignatures: TypeSignature*): Constructor =
    Constructor(name, typeSignatures.toList)
}

case class Constructor(name: String, typeSignatures: List[TypeSignature])