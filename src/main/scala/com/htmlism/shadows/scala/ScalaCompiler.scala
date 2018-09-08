package com.htmlism.shadows
package scala

import com.htmlism.shadows.plato.DataClass

object ScalaCompiler extends Transpiler[plato.DataClass, List[Template]] {
  def transpile(a: DataClass): List[Template] =
    sealedTrait(a) ++ constructors(a)

  private def sealedTrait(a: DataClass) = {
    val tps = a.typeParameters.list.toList.map(_.name)

    List {
      Trait(a.name, isSealed = true, typeParameters = tps, supers = Nil)
    }
  }

  private def constructors(a: DataClass) =
    a.constructors.map { c =>
      if (c.typeSignatures.isEmpty) {
        ScalaObject(c.name, isCase = true, typeParameters = Nil, supers = Nil)
      } else {
        ScalaClass(c.name, isCase = true, typeParameters = Nil, supers = Nil)
      }
    }.list.toList
}
