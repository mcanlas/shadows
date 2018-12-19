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
