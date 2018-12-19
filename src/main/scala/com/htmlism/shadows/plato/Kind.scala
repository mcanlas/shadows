package com.htmlism.shadows.plato

sealed trait Kind {
  def name: String
}

sealed trait Star     extends Kind
sealed trait StarStar extends Kind

case class NullaryTypeConstructor(name: String) extends Star
case class UnaryTypeConstructor(name: String)   extends StarStar

case class ConstrainedNtc(name: String, constraint: OverStar)     extends Star
case class ConstrainedUtc(name: String, constraint: OverStarStar) extends StarStar
