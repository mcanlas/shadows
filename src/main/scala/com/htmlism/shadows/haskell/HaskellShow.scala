package com.htmlism.shadows
package haskell

object HaskellShow extends ShadowShow[TopDeclaration]:
  def show(x: TopDeclaration): String =
    x match
      case d: DataDeclaration => dataShow(d)
      case t: TypeClass       => typeShow(t)

  private def dataShow(x: DataDeclaration): String =
    val constructors = x.constructors.map(cToStr).mkString(" | ")

    val left =
      (List(x.name) ::: x.typeParameters)
        .mkString(" ")

    s"data $left = $constructors"

  private def typeShow(x: TypeClass): String =
    s"class ${x.superTypes.mkString} ${x.name} where"

  private def cToStr(c: Constructor) =
    val parameters =
      if (c.arguments.isEmpty)
        ""
      else
        " " + c
          .arguments
          .map(showTs)
          .mkString(" ")

    s"${c.name}$parameters"

  private def showTs(ts: TypeSignature): String =
    ts match
      case haskell.Proper(s)            =>
        s
      case haskell.ConstructedOne(f, a) =>
        s"($f ${showTs(a)})"
