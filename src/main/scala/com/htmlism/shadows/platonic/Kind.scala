package com.htmlism.shadows.platonic

case class Kind(name: String) {
  def of(a: String): UnaryType =
    UnaryType(this, NullaryType(a))
}