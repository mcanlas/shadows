package com.htmlism.shadows
package scala

import com.htmlism.shadows.plato.DataClass

object ScalaCompiler extends Transpiler[plato.DataClass, List[Template]] {
  def transpile(a: DataClass): List[Template] =
    sealedTrait(a) ++ constructors(a)

  private def sealedTrait(a: DataClass) =
    List {
      Trait(a.name, isSealed = true, Nil)
    }

  private def constructors(a: DataClass) =
    a.constructors.map { c =>
      if (c.typeSignatures.isEmpty)
        ScalaObject(c.name, isCase = true, Nil)
      else
        ScalaClass(c.name, isCase = true)
    }.list.toList
}
