package com.htmlism.shadows
package haskell

object HaskellCompiler extends Transpiler[plato.DataClass, DataDeclaration] {
  def transpile(a: plato.DataClass): DataDeclaration = {
    val base = DataDeclaration(a.name, a.typeParameters.map(_.name.toLowerCase): _*)

    val withCons =
      a.constructors.list.foldLeft(base)((b, tc) => b.copy(constructors = b.constructors :+ consToCons(tc)))

    withCons
  }

  private def consToCons(cons: plato.Constructor): Constructor =
    Constructor(
      cons.name,
      cons.parameters.map(_.sig).map {
        case plato.TypeLiteral(s) =>
          haskell.Proper(s.toLowerCase)

        case plato.TypeVariable(s) =>
          haskell.Proper(s.toLowerCase)

        case plato.ConstructedLiteral(f, a) =>
          haskell.ConstructedOne(f, "yy")

        case plato.ConstructedVariable(f, a) =>
          haskell.ConstructedOne(f, "xx")

        case plato.FunctionConsType(a, b) =>
          throw new IllegalStateException
      }
    )
}
