package com.htmlism.shadows.plato

case class DataClass(name: String, typeParameters: Nel[NullaryTypeConstructor], constructors: Nel[Constructor])

case class Constructor(name: String, typeParameters: List[NullaryTypeConstructor])