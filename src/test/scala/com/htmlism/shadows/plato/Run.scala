package com.htmlism.shadows
package plato

import scalaz._, Scalaz._

object Run extends App {
  private val boolean =
    DataClass("Boolean",
              Nil,
              Nel(
                Constructor("True"),
                Constructor("False")
              ))

  // polymorphic
  private val option =
    DataClass("Option",
              List('A.ntc),
              Nel(
                Constructor("Some", Ax),
                Constructor("None")
              ))

  // polymorphic
  private val list =
    DataClass("List",
              List('A.ntc),
              Nel(
                Constructor("Cons", Ax, Parameter("xs", ConstructedOne("List", "A"))),
                Constructor("Nil")
              ))

  // polymorphic over two parameters
  private val either =
    DataClass("Either",
              List('A.ntc, 'B.ntc),
              Nel(
                Constructor("Left", Ax),
                Constructor("Right", Parameter("x", B))
              ))

  /**
    * In Haskell, a constructor can have same name as the data type.
    *
    * In Scala, this can be true for nullary constructors (case objects) but not for
    * other constructors (case classes).
    */
  // example of one constructor
  private val nel =
    DataClass("NonEmptyList",
              List('A.ntc),
              Nel(
                Constructor("Nel", Ax, Parameter("xs", ConstructedOne("List", "A")))
              ))

  private val functor =
    TypeClass
      .k1("Functor", 'F.utc)
      .w(Method("map", FA =>: (A =>: B) =>: FB))

  private val applicative =
    TypeClass
      .k1("Applicative", ConstrainedUtc("F", functor))
      .w(Method("pure", A =>: FA))

  private val monad =
    TypeClass
      .k1("Monad", ConstrainedUtc("F", applicative))
      .w(Method("flatMap", FA =>: (A =>: FB) =>: FB))

  private val semigroup =
    TypeClass
      .k0("Semigroup", 'A.ntc)
      .w(Method("plus", A =>: A =>: A))

  private val monoid =
    TypeClass
      .k0("Monoid", ConstrainedNtc("A", semigroup))
      .w(Method("zero", A))

  for (tc <- List(functor, applicative, monad, semigroup, monoid)) {
    println {
      implicitly[HaskellShow[TypeClass]].show(tc)
    }

    println

    println {
      implicitly[SimulacrumShow[TypeClass]].show(tc)
    }

    println
  }

  for (tc <- List(option, list, either, nel)) {
    println {
      implicitly[ScalaShow[DataClass]].show(tc)
    }

    println("\n--\n")
  }

  /**
    * Prints translated source code given a transpiler and some code.
    *
    * @param c A transpiler from language `A` to language `B`
    * @param x An expression in language `A`
    *
    * @tparam A A source language
    * @tparam B A destination language
    */
  def show[A, B: ShadowShow](c: Transpiler[A, B])(x: A): Unit =
    println {
      x |>
        c.transpile |>
        implicitly[ShadowShow[B]].show
    }

  List(boolean, option, list, either, nel)
    .foreach { d =>
      println("\n------\n")

      show(haskell.HaskellCompiler)(d)

      println("\n--\n")

      show(scala.ScalaCompiler)(d)
    }
}
