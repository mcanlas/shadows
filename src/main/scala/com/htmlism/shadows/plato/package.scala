package com.htmlism.shadows

import scalaz.NonEmptyList

package object plato {
  type Nel[A] = NonEmptyList[A]

  object TypeClass {
    def k0(name: String, parameter: NullaryTypeParameter): BasicTypeClass =
      BasicTypeClass(name, parameter, Nil)

    def k1(name: String, parameter: UnaryTypeParameter): ConstructorClass =
      ConstructorClass(name, parameter, Nil)
  }

  sealed trait TypeClass {
    def name: String

    def methods: List[Method]
  }

  case class BasicTypeClass(name: String, parameter: NullaryTypeParameter, methods: List[Method]) extends TypeClass {
    def w(m: Method): BasicTypeClass = copy(methods = m +: methods)
  }

  case class ConstructorClass(name: String, parameter: UnaryTypeParameter, methods: List[Method]) extends TypeClass {
    def w(m: Method): ConstructorClass = copy(methods = m +: methods)
  }

  sealed trait TypeParameter {
    def name: String
  }

  sealed trait NullaryTypeParameter extends TypeParameter
  sealed trait UnaryTypeParameter extends TypeParameter

  case class NullaryTypeConstructor(name: String) extends NullaryTypeParameter
  case class UnaryTypeConstructor(name: String) extends UnaryTypeParameter

  case class ConstrainedNtc(name: String, constraint: BasicTypeClass) extends NullaryTypeParameter
  case class ConstrainedUtc(name: String, constraint: ConstructorClass) extends UnaryTypeParameter

  object Method {
    def apply(name: String, signature: TypeSignature): Method =
      Method(name, None, signature)
  }
  case class Method(name: String, symbolicAlias: Option[String], signature: TypeSignature)

  sealed trait TypeSignature {
    def =>:(left: TypeSignature): FunctionType =
      FunctionType(left, this)
  }

  sealed trait TerminalTypeSignature extends TypeSignature

  case class BasicType(name: String) extends TerminalTypeSignature
  case class ConstructedOne(f: String, a: String) extends TerminalTypeSignature

  case class FunctionType(a: TypeSignature, b: TypeSignature) extends TypeSignature

  val A = BasicType("A")
  val B = BasicType("B")
  val FA = ConstructedOne("F", "A")
  val FB = ConstructedOne("F", "B")
}
