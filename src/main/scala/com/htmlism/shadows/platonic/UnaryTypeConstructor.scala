package com.htmlism.shadows.platonic

case class UnaryTypeConstructor(name: String) {
  def of(a: String): AppliedType =
    AppliedType(this, NullaryType(a))
}