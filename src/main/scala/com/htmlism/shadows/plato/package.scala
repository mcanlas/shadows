package com.htmlism.shadows

import scalaz.NonEmptyList

package object plato {
  type Nel[A] = NonEmptyList[A]

  object TypeClass {
    def k0(name: String, parameter: UnaryTypeParameter): TypeClass =
      BasicTypeClass(name, parameter, Nil)

    def k1(name: String, parameter: NullaryTypeParameter): TypeClass =
      ConstructorClass(name, parameter, Nil)
  }

  sealed trait TypeClass {
    def name: String

    def methods: List[Method]
  }

  case class BasicTypeClass(name: String, parameter: UnaryTypeParameter, methods: List[Method]) extends TypeClass

  case class ConstructorClass(name: String, parameter: NullaryTypeParameter, methods: List[Method]) extends TypeClass

  sealed trait TypeParameter {
    def name: String
  }

  sealed trait UnaryTypeParameter extends TypeParameter
  sealed trait NullaryTypeParameter extends TypeParameter

  case class NullaryTypeConstructor(name: String) extends UnaryTypeParameter
  case class UnaryTypeConstructor(name: String) extends NullaryTypeParameter

  case class ConstrainedNtc(name: String, constraint: BasicTypeClass) extends UnaryTypeParameter
  case class ConstrainedUtc(name: String, constraint: ConstructorClass) extends NullaryTypeParameter

  case class Method(name: String, symbolicAlias: String, signature: TypeSignature)

  sealed trait TypeSignature

  sealed trait TerminalTypeSignature extends TypeSignature {
    def =>:(left: TypeSignature): FunctionType =
      FunctionType(left, this)
  }

  case class BasicType(name: String) extends TerminalTypeSignature
  case class ConstructedOne(f: String, a: String) extends TerminalTypeSignature

  case class FunctionType(a: TypeSignature, b: TerminalTypeSignature) extends TypeSignature
}
