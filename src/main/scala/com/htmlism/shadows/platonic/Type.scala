package com.htmlism.shadows.platonic

sealed trait Type

case class NullaryType(name: String) extends Type

case class UnaryType(kind: Kind, typeArgument: Type) extends Type

case class FunctionType(src: Type, sink: Type) extends Type

object FunctionType {
  def apply(a: String, b: String): FunctionType =
    FunctionType(NullaryType(a), NullaryType(b))
}