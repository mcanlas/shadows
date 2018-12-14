package com.htmlism.shadows
package haskell

object HaskellCompiler extends Transpiler[plato.DataClass, DataDeclaration] {
  def transpile(a: plato.DataClass): DataDeclaration = {
    val base = DataDeclaration(a.name, a.typeRegistry.map(_.toLowerCase): _*)

    val withCons =
      a.constructors.list.foldLeft(base)((b, tc) => b.copy(constructors = b.constructors :+ consToCons(tc)))

    withCons
  }

  private def consToCons(cons: plato.Constructor): Constructor =
    Constructor(
      cons.name,
      cons.parameters.map(_.sig).map(ts2ts)
    )

  private def ts2ts(x: plato.TypeSignature): TypeSignature =
    x match {
      case plato.TypeLiteral(s) =>
        haskell.Proper(s.toLowerCase)

      case plato.TypeVariable(s) =>
        haskell.Proper(s.toLowerCase)

      case plato.ConstructedLiteral(f, a) =>
        haskell.ConstructedOne(f, ts2ts(a))

      case plato.ConstructedVariable(f, a) =>
        haskell.ConstructedOne(f, ts2ts(a))

      case plato.FunctionConsType(a, b) =>
        throw new IllegalStateException
    }

}
