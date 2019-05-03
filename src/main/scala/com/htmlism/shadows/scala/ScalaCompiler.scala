package com.htmlism.shadows
package scala

import com.htmlism.shadows.plato.{DataClass, TypeClass, TypeSignature}

/**
  * Possible degenerate case. If a data class only has one constructor, it doesn't need
  * a separate trait in addition to its constructor.
  */
object ScalaCompiler extends Transpiler[plato.PlatonicConstruct, Template] {
  def transpile(a: plato.PlatonicConstruct): List[Template] =
    a match {
      case dc @ DataClass(_, _) => transpileDc(dc)
      case _: TypeClass         => ???
    }

  private def transpileDc(a: DataClass) =
    sealedTrait(a) ++ constructors(a)

  private def sealedTrait(a: DataClass) = {
    val tps = a.typeRegistry.map("+" + _)

    List {
      Trait(a.name, isSealed = true, typeParameters = tps, supers = Nil)
    }
  }

  private def constructors(a: DataClass) =
    a.constructors.map { c =>
      val supers =
        if (a.typeRegistry.isEmpty)
          List(a.name)
        else {
          val slug =
            a.constructors.toList
              .flatMap { c2 =>
                if (c2 == c)
                  DataClass
                    .typeRegistry(c2)
                else
                  DataClass
                    .typeRegistry(c2)
                    .map(_ => "Nothing")
              }
              .mkString(", ")

          List(a.name + s"[$slug]")
        }

      if (c.parameters.isEmpty) {
        ScalaObject(c.name, isCase = true, typeParameters = Nil, supers)
      } else {
        val parameters =
          c.parameters
            .map { p =>
              p.name + ": " + sigToStr(p.sig)
            }

        ScalaClass(c.name, isCase = true, typeParameters = DataClass.typeRegistry(c), supers, parameters)
      }
    }.toList

  private def sigToStr(sig: TypeSignature): String =
    sig match {
      case plato.TypeLiteral(s) =>
        s

      case plato.TypeVariable(s) =>
        s

      case plato.ConstructedLiteral(f, a) =>
        s"$f[${sigToStr(a)}]"

      case plato.ConstructedVariable(f, a) =>
        s"$f[${sigToStr(a)}]"

      case plato.FunctionConsType(a, b) =>
        sigToStr(a) + " => " + sigToStr(b)
    }
}
