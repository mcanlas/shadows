package com.htmlism.shadows

trait Transpiler[A, B] {
  def transpile(a: A): B
}
