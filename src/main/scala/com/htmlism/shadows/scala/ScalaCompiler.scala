package com.htmlism.shadows
package scala

import com.htmlism.shadows.plato.DataClass

object ScalaCompiler extends Transpiler[plato.DataClass, List[Template]] {
  def transpile(a: DataClass): List[Template] =
    sealedTrait(a) ++ constructors(a)

  private def sealedTrait(a: DataClass) =
    List {
      Trait(a.name, isSealed = true)
    }

  private def constructors(a: DataClass) =
    a.constructors.map { c =>
      if (c.typeSignatures.isEmpty)
        ScalaObject(c.name)
      else
        ScalaClass(c.name)
    }.list.toList
}
