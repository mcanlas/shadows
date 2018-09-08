package com.htmlism.shadows
package scala

import com.htmlism.shadows.plato.DataClass

/**
 * Possible degenerate case. If a data class only has one constructor, it doesn't need
 * a separate trait in addition to its constructor.
 */
object ScalaCompiler extends Transpiler[plato.DataClass, List[Template]] {
  def transpile(a: DataClass): List[Template] =
    sealedTrait(a) ++ constructors(a)

  private def sealedTrait(a: DataClass) = {
    val tps = a.typeParameters.map(_.name)

    List {
      Trait(a.name, isSealed = true, typeParameters = tps, supers = Nil)
    }
  }

  private def constructors(a: DataClass) =
    a.constructors.map { c =>
      val supers =
        if (a.typeParameters.isEmpty)
          List(a.name)
        else
          List(a.name + "[]")

      if (c.parameters.isEmpty) {
        ScalaObject(c.name, isCase = true, typeParameters = Nil, supers)
      } else {
        ScalaClass(c.name, isCase = true, typeParameters = Nil, supers)
      }
    }.list.toList
}
