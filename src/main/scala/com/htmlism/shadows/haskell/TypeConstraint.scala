package com.htmlism.shadows.haskell

sealed trait TypeConstraint {
  def name: String
}

case class UnconstrainedType(name: String) extends TypeConstraint

case class ConstrainedType(constraint: String, name: String) extends TypeConstraint
