package com.htmlism.shadows
package haskell

object HaskellShow extends ShadowShow[DataDeclaration] {
  def show(x: DataDeclaration): String = {
    val constructors = x.constructors.map(cToStr).mkString(" | ")

    val left =
      (List(x.name) ::: x.typeParameters)
        .mkString(" ")

    s"data $left = $constructors"
  }

  private def cToStr(c: Constructor) = {
    val parameters =
      if (c.arguments.isEmpty)
        ""
      else
        " " + c.arguments
          .map {
            case haskell.Proper(s) =>
              s
            case haskell.ConstructedOne(f, a) =>
              s"($f $a)"
          }
          .mkString(" ")

    s"${c.name}$parameters"
  }
}
