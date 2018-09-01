package com.htmlism.shadows.plato

case class DataClass(name: String, typeParameters: Nel[NullaryTypeConstructor], constructors: Nel[Constructor])

object Constructor {
  def apply(name: String, typeParameters: NullaryTypeConstructor*) =
    Constructor(name, typeParameters.toList)
}

case class Constructor(name: String, typeParameters: List[NullaryTypeConstructor])