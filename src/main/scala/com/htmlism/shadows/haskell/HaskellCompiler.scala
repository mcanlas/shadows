package com.htmlism.shadows
package haskell

object HaskellCompiler extends Transpiler[plato.PlatonicConstruct, TopDeclaration] {
  def transpile(a: plato.PlatonicConstruct): List[TopDeclaration] =
    a match {
      case dc: plato.DataClass => transpileDc(dc)
    }

  private def transpileDc(a: plato.DataClass) = {
    val base = DataDeclaration(a.name, a.typeRegistry.map(_.toLowerCase): _*)

    val withCons =
      a.constructors.list.foldLeft(base)((b, tc) => b.copy(constructors = b.constructors :+ consToCons(tc)))

    List(withCons)
  }

  private def consToCons(cons: plato.Constructor): Constructor =
    Constructor(
      cons.name,
      cons.parameters.map(_.sig).map(ts2ts)
    )

  private def ts2ts(x: plato.TypeSignature): TypeSignature =
    x match {
      case plato.TypeLiteral(s) =>
        haskell.Proper(s)

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
