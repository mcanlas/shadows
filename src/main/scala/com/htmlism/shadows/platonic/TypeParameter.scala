package com.htmlism.shadows.platonic

sealed trait TypeParameter

case class UnaryHole(name: String, evidence: List[TypeClass]) extends TypeParameter

object UnaryHole {
  def apply(s: String): TypeParameter =
    UnaryHole(s, Nil)
}