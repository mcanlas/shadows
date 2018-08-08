package com.htmlism.shadows.platonic

import scalaz.NonEmptyList

sealed trait MethodLike

case class Method(name: String, parameters: List[Parameter], returnType: Type) extends MethodLike

case class PolymorphicMethod(name: String, typeParameters: NonEmptyList[TypeParameter], parameters: List[Parameter], returnType: Type) extends MethodLike
