package com.htmlism.shadows

/**
  * A contract for converting constructs of language `A` to language `B`.
  *
  * @tparam A
  *   The source language
  * @tparam B
  *   The destination language
  */
trait Transpiler[A, B] {
  def transpile(a: A): List[B]
}
