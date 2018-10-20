package com.htmlism.shadows.plato

object TypeClass {
  def k0(name: String, parameter: Star): OverStar =
    OverStar(name, parameter, Nil)

  def k1(name: String, parameter: StarStar): OverStarStar =
    OverStarStar(name, parameter, Nil)
}

sealed trait TypeClass {
  def name: String

  def methods: List[Method]
}

case class OverStar(name: String, parameter: Star, methods: List[Method]) extends TypeClass {
  def w(m: Method): OverStar = copy(methods = m +: methods)
}

case class OverStarStar(name: String, parameter: StarStar, methods: List[Method]) extends TypeClass {
  def w(m: Method): OverStarStar = copy(methods = m +: methods)
}

sealed trait Kind {
  def name: String
}

sealed trait Star     extends Kind
sealed trait StarStar extends Kind

case class NullaryTypeConstructor(name: String) extends Star
case class UnaryTypeConstructor(name: String)   extends StarStar

case class ConstrainedNtc(name: String, constraint: OverStar)     extends Star
case class ConstrainedUtc(name: String, constraint: OverStarStar) extends StarStar

object Method {
  def apply(name: String, signature: TypeSignature): Method =
    Method(name, None, signature)
}
case class Method(name: String, symbolicAlias: Option[String], signature: TypeSignature)

sealed trait TypeSignature {
  def =>:(left: TypeSignature): FunctionConsType =
    FunctionConsType(left, this)
}

sealed trait TerminalTypeSignature extends TypeSignature

case class BasicType(name: String)              extends TerminalTypeSignature
case class ConstructedOne(f: String, a: String) extends TerminalTypeSignature

case class FunctionConsType(a: TypeSignature, b: TypeSignature) extends TypeSignature
