package com.htmlism.shadows
package scala

import com.htmlism.shadows.plato.DataClass

object ScalaCompiler extends Transpiler[plato.DataClass, List[Trait]] {
  def transpile(a: DataClass): List[Trait] =
    Nil
}
